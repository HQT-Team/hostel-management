package com.hqt.happyhostel.dao;

import com.hqt.happyhostel.dto.*;
import com.hqt.happyhostel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDAO {

    public static boolean addNewRoom(int hostelID, int roomNumber, int capacity, double roomArea, int attic,
                                     String infrastructure1, int quantity1, int status1,
                                     String infrastructure2, int quantity2, int status2,
                                     String infrastructure3, int quantity3, int status3) {
        Connection cn = null;
        Boolean isInserted = false;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "INSERT INTO Rooms (hostel_id, room_number, capacity, room_area, has_attic, room_status)\n" +
                        "VALUES (?, ?, ?, ?, ?, 1)\n" +
                        "DECLARE @roomID int = SCOPE_IDENTITY()\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);
                pst.setInt(2, roomNumber);
                pst.setInt(3, capacity);
                pst.setDouble(4, roomArea);
                pst.setInt(5, attic);

                pst.setString(6, infrastructure1);
                pst.setInt(7, quantity1);
                pst.setInt(8, status1);

                pst.setString(9, infrastructure2);
                pst.setInt(10, quantity2);
                pst.setInt(11, status2);

                pst.setString(12, infrastructure3);
                pst.setInt(13, quantity3);
                pst.setInt(14, status3);

                int rows = pst.executeUpdate();
                if (rows != 0) {
                    cn.commit();
                    isInserted = true;
                } else {
                    cn.rollback();
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

    public static boolean addNewManyRooms(int hostelID, int capacity, double roomArea, int attic,
                                          String infrastructure1, int quantity1, int status1,
                                          String infrastructure2, int quantity2, int status2,
                                          String infrastructure3, int quantity3, int status3) {
        Connection cn = null;
        Boolean isInserted = false;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "DECLARE @room_number int = (SELECT TOP 1 room_number\n" +
                        "\t\t\t\t\t\t\tFROM dbo.Rooms\n" +
                        "\t\t\t\t\t\t\tORDER BY room_number DESC)\n" +
                        "IF @room_number is NULL\n" +
                        "    SET @room_number = 1\n" +
                        "ELSE\n" +
                        "\tSET @room_number = @room_number + 1\n" +
                        "INSERT INTO Rooms (hostel_id, room_number, capacity, room_area, has_attic, room_status)\n" +
                        "VALUES (?, @room_number, ?, ?, ?, 1)\n" +
                        "DECLARE @roomID int = SCOPE_IDENTITY()\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)\n" +
                        "INSERT INTO Infrastructures (room_id, name, quantity, status) VALUES (@roomID, ?, ?, ?)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);
                pst.setInt(2, capacity);
                pst.setDouble(3, roomArea);
                pst.setInt(4, attic);

                pst.setString(5, infrastructure1);
                pst.setInt(6, quantity1);
                pst.setInt(7, status1);

                pst.setString(8, infrastructure2);
                pst.setInt(9, quantity2);
                pst.setInt(10, status2);

                pst.setString(11, infrastructure3);
                pst.setInt(12, quantity3);
                pst.setInt(13, status3);

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
                String sql = "SELECT I.id, I.room_id, I.name, I.quantity, I.status\n" +
                        "FROM Rooms R, Infrastructures I\n" +
                        "WHERE R.room_id = I.room_id \n" +
                        "\tAND R.room_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        int room_id = rs.getInt("room_id");
                        String name = rs.getString("name");
                        int quantity = rs.getInt("quantity");
                        int status = rs.getInt("status");
                        infrastructures.add(new Infrastructures(id, room_id, name, quantity, status));
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

    public static Hostels getHostelInformationByHostelID(int hostelID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Hostels hostel = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT hostel_id, account_id, name, address, ward, district, city\n" +
                        "FROM Hostels\n" +
                        "WHERE hostel_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int hostel_owner_account_id = rs.getInt("account_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String ward = rs.getString("ward");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    hostel = new Hostels(hostelID, hostel_owner_account_id, name, address, ward, district, city);
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
        return hostel;
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

    public static Invoices getNearestInvoice(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Invoices invoice = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT invoice_id, room_id, total_money, consume_month, consume_year, created_date, expired_payment_date, payment_status\n" +
                        "FROM Invoices\n" +
                        "WHERE room_id = ?\n" +
                        "ORDER BY consume_year DESC, consume_month DESC ";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int invoiceID = rs.getInt("invoice_id");
                    int totalMoney = rs.getInt("total_money");
                    int consumeMonth = rs.getInt("consume_month");
                    int consumeYear = rs.getInt("consume_year");
                    String createdDate = rs.getString("created_date");
                    String expiredPaymentDate = rs.getString("expired_payment_date");
                    int paymentStatus = rs.getInt("payment_status");
                    invoice = new Invoices(invoiceID, roomID, totalMoney, consumeMonth, consumeYear, createdDate, expiredPaymentDate, paymentStatus);
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
        return invoice;
    }

    public static Payment getNearestPayments(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Payment payment = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT TOP 1 P.payment_id as 'payment_id', P.payment_date as 'payment_date', P.payment_method as 'payment_method', P.invoice_id as 'invoice_id'\n" +
                        "FROM Invoices, Payments P\n" +
                        "WHERE room_id = ?\n" +
                        "AND Invoices.invoice_id = P.invoice_id\n" +
                        "ORDER BY consume_year DESC, consume_month DESC";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int paymentID = rs.getInt("payment_id");
                    String paymentDate = rs.getString("payment_date");
                    String paymentMethod = rs.getString("payment_method");
                    int invoiceID = rs.getInt("invoice_id");
                    payment = new Payment(paymentID, paymentDate, paymentMethod, invoiceID);
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
        return payment;
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

    public static ArrayList<RoommateInformation> getRoommateInformation(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Consume consume = null;
        ArrayList<RoommateInformation> roommateInformationArrayList = new ArrayList();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT roomate_info_id, renter_account_id, fullname, email, birthday, sex, phone, address, CCCD, parent_name, parent_phone\n" +
                        "FROM RoomateInformations\n" +
                        "WHERE renter_account_id = (SELECT renter_account_id\n" +
                        "\t\t\t\t\t\t\tFROM RenterAccounts\n" +
                        "\t\t\t\t\t\t\tWHERE room_id = ?)";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int roommateInfoID = rs.getInt("roomate_info_id");
                        int renterAccountID = rs.getInt("renter_account_id");
                        String fullName = rs.getString("fullname");
                        String email = rs.getString("email");
                        String birthday = rs.getString("birthday");
                        int sex = rs.getInt("sex");
                        String phone = rs.getString("phone");
                        String address = rs.getString("address");
                        String CCCD = rs.getString("CCCD");
                        String parent_name = rs.getString("parent_name");
                        String parent_phone = rs.getString("parent_phone");
                        roommateInformationArrayList.add(new RoommateInformation(roommateInfoID, renterAccountID, fullName, email, birthday, sex, phone, address, CCCD, parent_name, parent_phone));
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

    public static Room getRoomInformation(int roomID) {
        Connection cn = null;
        PreparedStatement pst = null;
        Room room = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT hostel_id, room_number, capacity, room_status, room_area, has_attic\n" +
                        "FROM Rooms\n" +
                        "WHERE room_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);

                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int hostelID = rs.getInt("hostel_id");
                    int roomNumber = rs.getInt("room_number");
                    int capacity = rs.getInt("capacity");
                    int roomStatus = rs.getInt("room_status");
                    double roomArea = rs.getDouble("room_area");
                    int hasAttic = rs.getInt("has_attic");
                    room = new Room(roomID, hostelID, roomNumber, capacity, roomStatus, roomArea, null, null, hasAttic);
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

    public static boolean updateRoom() {
        Connection cn = null;
        PreparedStatement pst = null;
        Boolean isSuccess = false;
        try {

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
