package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.*;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public static ArrayList<Room> getListRoomByHostelID(int hostelID) {
        Connection cn = null;
        ArrayList<Room> rooms = new ArrayList<>();
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                // Insert new room include Nha ve sinh, cua so, cua ra vao, may lanh theo thứ tự
                String sql = "SELECT room_id, hostel_id, room_number, capacity, room_area, has_attic, room_status\n" +
                        "FROM Rooms\n" +
                        "WHERE hostel_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int roomID = rs.getInt("room_id");
                        int roomNumber = rs.getInt("room_number");
                        int capacity = rs.getInt("capacity");
                        double roomArea = rs.getDouble("room_area");
                        int hasAttic = rs.getInt("has_attic");
                        int roomStatus = rs.getInt("room_status");
                        RoomInformation roomInformation = null;
                        rooms.add(new Room(roomID, hostelID, roomNumber, capacity, roomStatus, roomArea, hasAttic, roomInformation));
                    }
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return rooms;
    }

    public static int getNumberRoomSpecificHostel(int hostelID) {
        Connection cn = null;
        int number = 0;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                // Insert new room include Nha ve sinh, cua so, cua ra vao, may lanh theo thứ tự
                String sql = "SELECT COUNT(room_id) as 'quantity'\n" +
                        "FROM Rooms\n" +
                        "WHERE hostel_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    number = rs.getInt("quantity");
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return number;
    }

    public static ArrayList<ServiceInfo> getServicesOfHostel(int hostelID) {
        Connection cn = null;
        ArrayList<ServiceInfo> servicesList = new ArrayList<>();
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "SELECT HostelService.service_id as 'service_id', Services.service_name as 'service_name', HostelService.valid_date as 'valid_date', HostelService.service_price as 'service_price', Services.unit as 'unit'\n" +
                        "FROM HostelService, Services\n" +
                        "WHERE hostel_id = ?\n" +
                        "AND HostelService.service_id = Services.service_id\n" +
                        "AND valid_date IN (SELECT TOP 1 valid_date\n" +
                        "FROM HostelService\n" +
                        "WHERE hostel_id = ?\n" +
                        "AND valid_date < GETDATE()\n" +
                        "ORDER BY valid_date DESC)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);
                pst.setInt(2, hostelID);

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int serviceID = rs.getInt("service_id");
                        String serviceName = rs.getString("service_name");
                        int servicePrice = rs.getInt("service_price");
                        String unit = rs.getString("unit");
                        String validDate = rs.getString("valid_date");
                        servicesList.add(new ServiceInfo(hostelID, serviceID, serviceName, validDate, servicePrice, unit));
                    }
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return servicesList;
    }


    public static boolean addNewRoom(int hostelID, int roomNumber, int capacity, double roomArea, int attic, int roomStatus,
                                     int quantity1, int status1,
                                     int quantity2, int status2,
                                     int quantity3, int status3,
                                     int quantity4, int status4) {
        Connection cn = null;
        Boolean isInserted = false;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                // Insert new room include Nha ve sinh, cua so, cua ra vao, may lanh theo thứ tự
                String sql = "INSERT INTO Rooms (hostel_id, room_number, capacity, room_area, has_attic, room_status)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?)\n" +
                        "DECLARE @roomID int = SCOPE_IDENTITY()\n" +
                        "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (@roomID, ?, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Nhà vệ sinh'))\n" +
                        "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (@roomID, ?, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Cửa sổ'))\n" +
                        "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (@roomID, ?, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Cửa ra vào'))\n" +
                        "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (@roomID, ?, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Máy lạnh'))";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);
                pst.setInt(2, roomNumber);
                pst.setInt(3, capacity);
                pst.setDouble(4, roomArea);
                pst.setInt(5, attic);
                pst.setInt(6, roomStatus);

                pst.setInt(7, quantity1);
                pst.setInt(8, status1);

                pst.setInt(9, quantity2);
                pst.setInt(10, status2);

                pst.setInt(11, quantity3);
                pst.setInt(12, status3);

                pst.setInt(13, quantity4);
                pst.setInt(14, status4);

                int rows = pst.executeUpdate();
                if (rows != 0) {
                    isInserted = true;
                } else {
                    isInserted = false;
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isInserted;
    }

    public static boolean addNewManyRooms(int hostelID, int capacity, double roomArea, int attic, int roomStatus,
                                          int quantity1, int status1,
                                          int quantity2, int status2,
                                          int quantity3, int status3,
                                          int quantity4, int status4) {
        Connection cn = null;
        Boolean isInserted = false;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "DECLARE @room_number int = (SELECT TOP 1 room_number\n" +
                        "FROM dbo.Rooms\n" +
                        "WHERE hostel_id = ?\n" +
                        "ORDER BY room_number DESC)\n" +
                        "IF @room_number is NULL\n" +
                        "\tSET @room_number = 1\n" +
                        "ELSE\n" +
                        "\tSET @room_number = @room_number + 1\n" +
                        "INSERT INTO Rooms (hostel_id, room_number, capacity, room_area, has_attic, room_status)\n" +
                        "VALUES (?, @room_number, ?, ?, ?, ?)\n" +
                        "DECLARE @roomID int = SCOPE_IDENTITY()\n" +
                        "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (@roomID, ?, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Nhà vệ sinh'))\n" +
                        "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (@roomID, ?, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Cửa sổ'))\n" +
                        "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (@roomID, ?, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Cửa ra vào'))\n" +
                        "INSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "VALUES (@roomID, ?, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Máy lạnh'))";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);
                pst.setInt(2, hostelID);
                pst.setInt(3, capacity);
                pst.setDouble(4, roomArea);
                pst.setInt(5, attic);
                pst.setInt(6, roomStatus);

                pst.setInt(7, quantity1);
                pst.setInt(8, status1);

                pst.setInt(9, quantity2);
                pst.setInt(10, status2);

                pst.setInt(11, quantity3);
                pst.setInt(12, status3);

                pst.setInt(13, quantity4);
                pst.setInt(14, status4);

                int rows = pst.executeUpdate();
                if (rows != 0) {
                    isInserted = true;
                } else {
                    isInserted = false;
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isInserted;
    }

    public static ArrayList<Infrastructures> getInfrastructures(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ArrayList<Infrastructures> infrastructures = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT id_infrastructure, quantity, status, infrastructure_name\n" +
                        "FROM InfrastructuresRoom I, InfrastructureItem IT\n" +
                        "WHERE I.room_id = ?\n" +
                        "AND I.id_infrastructure_item = IT.id_infrastructure_item";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("id_infrastructure");
                        String name = rs.getString("infrastructure_name");
                        int quantity = rs.getInt("quantity");
                        int status = rs.getInt("status");
                        infrastructures.add(new Infrastructures(id, name, quantity, status));
                    }
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return infrastructures;
    }

    public static Room getRoomInformationByRoomID(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Room room = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT room_id, H.hostel_id as 'hostel_id', room_number, capacity, room_status, room_area, has_attic, name, address, ward, district, city\n" +
                        "FROM Rooms R, Hostels H\n" +
                        "WHERE R.room_id = ?\n" +
                        "AND R.hostel_id = H.hostel_id";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int hostelId = rs.getInt("hostel_id");
                    int roomNumber = rs.getInt("room_number");
                    int capacity = rs.getInt("capacity");
                    int roomStatus = rs.getInt("room_status");
                    double roomArea = rs.getDouble("room_area");
                    int hasAttic = rs.getInt("has_attic");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String ward = rs.getString("ward");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    room = new Room(roomID, hostelId, roomNumber, capacity, roomStatus, roomArea, hasAttic, new RoomInformation(name, address, ward, district, city));
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return room;
    }

    public static Contract getContract(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Contract contract = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT contract_id, room_id, price, start_date, expiration, deposit\n" +
                        "FROM Contracts\n" +
                        "WHERE room_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int contract_id = rs.getInt("contract_id");
                    int price = rs.getInt("price");
                    String startDate = rs.getString("start_date");
                    String expiration = rs.getString("expiration");
                    int deposit = rs.getInt("deposit");
                    contract = new Contract(contract_id, roomID, price, startDate, expiration, deposit);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return contract;
    }

    public static Bill getLastBill(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Bill bill = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1 bill_id, total_money, created_date, expired_payment_date, status, Bill.payment_id as 'payment_id', payment_name\n" +
                        "FROM Bill, Payment\n" +
                        "WHERE room_id = ?\n" +
                        "AND\tBill.payment_id = Payment.payment_id\n" +
                        "ORDER BY created_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int billID = rs.getInt("bill_id");
                    int totalMoney = rs.getInt("total_money");
                    String createdDate = rs.getString("created_date");
                    String expiredPaymentDate = rs.getString("expired_payment_date");
                    int status = rs.getInt("status");
                    int paymentID = rs.getInt("payment_id");
                    String paymentName = rs.getString("payment_name");
                    bill = new Bill(billID, roomID, totalMoney, createdDate, expiredPaymentDate, status, new Payment(paymentID, paymentName));
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bill;
    }

    public static Consume getNearestConsume(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Consume consume = null;
        ArrayList<Consume> consumesList = new ArrayList();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 2 consume_id, number_electric, number_water, start_consume_date, end_consume_date\n" +
                        "FROM Consumes\n" +
                        "WHERE room_id = ?\n" +
                        "ORDER BY end_consume_date DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int consumeID = rs.getInt("consume_id");
                        int numberElectric = rs.getInt("number_electric");
                        int numberWater = rs.getInt("number_water");
                        String startConsumeDate = rs.getString("start_consume_date");
                        String endConsumeDate = rs.getString("end_consume_date");
                        consumesList.add(new Consume(consumeID, roomID, numberElectric, numberWater, startConsumeDate, endConsumeDate));
                    }
                }
                if (consumesList.size() == 2) {
                    int consumeID = consumesList.get(0).getConsumeID();
                    String startDate = consumesList.get(0).getStartConsumeDate();
                    String endDate = consumesList.get(0).getEndConsumeDate();
                    int consumeElectricNumber = consumesList.get(0).getNumberElectric() - consumesList.get(1).getNumberElectric();
                    int consumeWaterNumber = consumesList.get(0).getNumberWater() - consumesList.get(1).getNumberWater();
                    consume = new Consume(consumeID, roomID, consumeElectricNumber, consumeWaterNumber, startDate, endDate);
                } else {
                    consume = consumesList.get(0);
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return consume;
    }

    public static ArrayList<RoommateInfo> getRoommateInformation(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ArrayList<RoommateInfo> roommateInformationArrayList = new ArrayList();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT roomate_info_id, fullname, email, birthday, sex, phone, address, CCCD, parent_name, parent_phone, account_renter_id\n" +
                        "FROM RoomateInformations\n" +
                        "WHERE account_renter_id = (SELECT TOP 1 account_id\n" +
                        "\t\t\t\t\t\tFROM Accounts\n" +
                        "\t\t\t\t\t\tWHERE room_id = ?\n" +
                        "\t\t\t\t\t\tAND role = 2\n" +
                        "\t\t\t\t\t\tAND status = 1\n" +
                        "\t\t\t\t\t\tORDER BY create_date DESC)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int roommateInfoID = rs.getInt("roomate_info_id");
                        String fullName = rs.getString("fullname");
                        String email = rs.getString("email");
                        String birthday = rs.getString("birthday");
                        int sex = rs.getInt("sex");
                        String phone = rs.getString("phone");
                        String address = rs.getString("address");
                        String CCCD = rs.getString("CCCD");
                        String parent_name = rs.getString("parent_name");
                        String parent_phone = rs.getString("parent_phone");
                        roommateInformationArrayList.add(new RoommateInfo(roommateInfoID, new Information(fullName, email, birthday, sex, phone, address, CCCD), parent_name, parent_phone));
                        roommateInformationArrayList.size();
                    }
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return roommateInformationArrayList;
    }
    public static Integer getQuantityMember(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        int quantity = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(roomate_info_id) as 'quantityMember'\n" +
                        "FROM RoomateInformations\n" +
                        "WHERE account_renter_id = (SELECT TOP 1 account_id\n" +
                        "\t\t\t\t\t\t\tFROM Accounts\n" +
                        "\t\t\t\t\t\t\tWHERE room_id = ?\n" +
                        "\t\t\t\t\t\t\tAND role = 2\n" +
                        "\t\t\t\t\t\t\tAND status = 1\n" +
                        "\t\t\t\t\t\t\tORDER BY create_date DESC)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    quantity = rs.getInt("quantityMember");
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return quantity;
    }

    public static boolean updateRoom(int roomID, int roomNumber, int capacity, double roomArea, int hasAttic
    ) {
        Connection cn = null;
        PreparedStatement pst = null;
        Boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sqlUpdateRoom = "UPDATE Rooms\n" +
                        "SET room_number = ?, capacity = ?, room_area = ?, has_attic = ?\n" +
                        "WHERE room_id = ?";

                pst = cn.prepareStatement(sqlUpdateRoom);
                pst.setInt(1, roomNumber);
                pst.setInt(2, capacity);
                pst.setDouble(3, roomArea);
                pst.setInt(4, hasAttic);
                pst.setInt(5, roomID);

                int rows = pst.executeUpdate();
                if (rows != 1) {
                    cn.rollback();
                } else {
                    isSuccess = true;
                    cn.commit();
                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isSuccess;
    }

}
