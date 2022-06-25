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

    public List<Room> getListRoomsByHostelId(int hostelID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                // Insert new room include Nha ve sinh, cua so, cua ra vao, may lanh theo thứ tự
                String sql = "SELECT room_id, hostel_id, room_number, capacity, room_area, has_attic, room_status\n" +
                        "FROM Rooms\n" +
                        "WHERE hostel_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);

                rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int roomID = rs.getInt("room_id");
                        int roomNumber = rs.getInt("room_number");
                        int capacity = rs.getInt("capacity");
                        double roomArea = rs.getDouble("room_area");
                        int hasAttic = rs.getInt("has_attic");
                        int roomStatus = rs.getInt("room_status");
                        RoomInformation roomInformation = null;
                        rooms.add(Room.builder()
                                .roomId(roomID)
                                .hostelId(hostelID)
                                .roomNumber(roomNumber)
                                .roomArea(roomArea)
                                .capacity(capacity)
                                .roomStatus(roomStatus)
                                .hasAttic(hasAttic)
                                .roomInformation(roomInformation)
                                .build());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
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

    public int getNumberRoomSpecificHostel(int hostelID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int number = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Insert new room include Nha ve sinh, cua so, cua ra vao, may lanh theo thứ tự
                String sql = "SELECT COUNT(room_id) as 'quantity'\n" +
                        "FROM Rooms\n" +
                        "WHERE hostel_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, hostelID);

                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    number = rs.getInt("quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
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

    public boolean addNewRoom(int hostelID, int roomNumber, int capacity, double roomArea, int attic, int roomStatus,
                              int quantity1, int status1,
                              int quantity2, int status2,
                              int quantity3, int status3,
                              int quantity4, int status4) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isInserted = false;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Insert new room include Nhà vệ sinh, cửa sổ, cửa ra vào, máy lạnh theo thứ tự
                String sql = "INSERT INTO Rooms (hostel_id, room_number, capacity, room_area, has_attic, room_status)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?)\n" +
                        "DECLARE @roomID int = SCOPE_IDENTITY()\n" +
                        "INSERT INTO Consumes (number_electric, number_water, update_date, status, room_id)\n" +
                        "VALUES (0, 0, GETDATE(), 1, @roomID)" +
                        "DECLARE @restQuantity int = ?\n" +
                        "WHILE ( @restQuantity > 0 )\n" +
                        "BEGIN\n" +
                        "\tINSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "\tVALUES (@roomID, 1, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Nhà vệ sinh'))\n" +
                        "\tSET @restQuantity = @restQuantity - 1\n" +
                        "END\n" +
                        "DECLARE @windowQuantity int = ?\n" +
                        "WHILE ( @windowQuantity > 0 )\n" +
                        "BEGIN\n" +
                        "\tINSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "\tVALUES (@roomID, 1, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Cửa sổ'))\n" +
                        "\tSET @windowQuantity = @windowQuantity - 1\n" +
                        "END\n" +
                        "DECLARE @doorQuantity int = ?\n" +
                        "WHILE ( @doorQuantity > 0 )\n" +
                        "BEGIN\n" +
                        "\tINSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "\tVALUES (@roomID, 1, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Cửa ra vào'))\n" +
                        "\tSET @doorQuantity = @doorQuantity - 1\n" +
                        "END\n" +
                        "DECLARE @airConditionQuantity int = ?\n" +
                        "WHILE ( @airConditionQuantity > 0 )\n" +
                        "BEGIN\n" +
                        "\tINSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "\tVALUES (@roomID, 1, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Máy lạnh'))\n" +
                        "\tSET @airConditionQuantity = @airConditionQuantity - 1\n" +
                        "END";

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

                if (pst.executeUpdate() > 0) {
                    isInserted = true;
                }
            }
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

    public boolean addNewManyRooms(int hostelID, int capacity, double roomArea, int attic, int roomStatus,
                                   int quantity1, int status1,
                                   int quantity2, int status2,
                                   int quantity3, int status3,
                                   int quantity4, int status4) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isInserted = false;
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
                        "INSERT INTO Consumes (number_electric, number_water, update_date, status, room_id)\n" +
                        "VALUES (0, 0, GETDATE(), 1, @roomID)" +
                        "DECLARE @restQuantity int = ?\n" +
                        "WHILE ( @restQuantity > 0 )\n" +
                        "BEGIN\n" +
                        "\tINSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "\tVALUES (@roomID, 1, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Nhà vệ sinh'))\n" +
                        "\tSET @restQuantity = @restQuantity - 1\n" +
                        "END\n" +
                        "DECLARE @windowQuantity int = ?\n" +
                        "WHILE ( @windowQuantity > 0 )\n" +
                        "BEGIN\n" +
                        "\tINSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "\tVALUES (@roomID, 1, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Cửa sổ'))\n" +
                        "\tSET @windowQuantity = @windowQuantity - 1\n" +
                        "END\n" +
                        "DECLARE @doorQuantity int = ?\n" +
                        "WHILE ( @doorQuantity > 0 )\n" +
                        "BEGIN\n" +
                        "\tINSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "\tVALUES (@roomID, 1, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Cửa ra vào'))\n" +
                        "\tSET @doorQuantity = @doorQuantity - 1\n" +
                        "END\n" +
                        "DECLARE @airConditionQuantity int = ?\n" +
                        "WHILE ( @airConditionQuantity > 0 )\n" +
                        "BEGIN\n" +
                        "\tINSERT INTO InfrastructuresRoom (room_id, quantity, status, id_infrastructure_item)\n" +
                        "\tVALUES (@roomID, 1, ?, (SELECT id_infrastructure_item FROM InfrastructureItem WHERE infrastructure_name = N'Máy lạnh'))\n" +
                        "\tSET @airConditionQuantity = @airConditionQuantity - 1\n" +
                        "END";

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
                if (pst.executeUpdate() > 0) {
                    isInserted = true;
                }
            }
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

    public Room getRoomInformationByRoomId(int roomID, int hostelID, int accountOwnerID) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Room room = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT room_id, H.hostel_id as 'hostel_id', room_number, capacity, room_status, room_area, has_attic, name, address, ward, district, city\n" +
                        "FROM Rooms R, Hostels H\n" +
                        "WHERE R.room_id = ?\n" +
                        "AND R.hostel_id = ?\n" +
                        "AND H.owner_account_id = ?\n" +
                        "AND R.hostel_id = H.hostel_id";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, roomID);
                pst.setInt(2, hostelID);
                pst.setInt(3, accountOwnerID);

                rs = pst.executeQuery();
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
                    RoomInformation roomInformation = RoomInformation.builder()
                            .hostelName(name)
                            .address(address)
                            .ward(ward)
                            .district(district)
                            .city(city)
                            .build();
                    room = Room.builder()
                            .roomId(roomID)
                            .hostelId(hostelId)
                            .roomNumber(roomNumber)
                            .roomStatus(roomStatus)
                            .capacity(capacity)
                            .roomArea(roomArea)
                            .hasAttic(hasAttic)
                            .roomInformation(roomInformation)
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
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

    public boolean updateRoom(int roomID, int roomNumber, int capacity, double roomArea, int hasAttic) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sqlUpdateRoom = "UPDATE Rooms\n" +
                        "SET room_number = ?, capacity = ?, room_area = ?, has_attic = ?\n" +
                        "WHERE room_id = ?";

                pst = cn.prepareStatement(sqlUpdateRoom);
                pst.setInt(1, roomNumber);
                pst.setInt(2, capacity);
                pst.setDouble(3, roomArea);
                pst.setInt(4, hasAttic);
                pst.setInt(5, roomID);

                if (pst.executeUpdate() == 0) {
                    cn.rollback();
                } else {
                    isSuccess = true;
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
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

    public boolean updateRoomStatus(int roomID, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean isSuccess = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sqlUpdateRoom = "UPDATE Rooms\n" +
                        "SET room_status = ?\n" +
                        "WHERE room_id = ?";

                pst = cn.prepareStatement(sqlUpdateRoom);
                pst.setInt(1, status);
                pst.setInt(2, roomID);

                if (pst.executeUpdate() <= 0) {
                    cn.rollback();
                } else {
                    isSuccess = true;
                    cn.commit();
                }
                cn.setAutoCommit(true);
            }
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

    public Room getRoomInfoByRenterId(int renterId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Room roomInfo = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT R.* FROM Rooms AS R INNER JOIN Contracts AS C \n" +
                        "ON R.room_id = C.room_id WHERE C.renter_id = ?";

                pst = cn.prepareStatement(sql);
                pst.setInt(1, renterId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int room_id = rs.getInt("room_id");
                    int hostel_id = rs.getInt("hostel_id");
                    int roomNumber = rs.getInt("room_number");
                    double roomArea = rs.getInt("room_area");
                    int capacity = rs.getInt("capacity");
                    roomInfo = Room
                            .builder()
                            .roomId(room_id)
                            .hostelId(hostel_id)
                            .roomNumber(roomNumber)
                            .capacity(capacity)
                            .roomArea(roomArea)
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return roomInfo;
    }

    public Room getRoomById(int roomId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Room roomInfor = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                pst = cn.prepareStatement(
                        "SELECT R.[room_id], R.[room_number], R.[room_area], R.[capacity], R.[has_attic], R.[hostel_id], R.[room_status]\n" +
                                "FROM [dbo].[Rooms] AS R\n" +
                                "WHERE R.[room_id]= ?");
                pst.setInt(1, roomId);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int roomID = rs.getInt("room_id");
                    int hostelId = rs.getInt("hostel_id");
                    int roomNumber = rs.getInt("room_number");
                    double roomArea = rs.getInt("room_area");
                    int capacity = rs.getInt("capacity");
                    int hasAttic = rs.getInt("has_Attic");
                    int roomStatus = rs.getInt("room_status");
                    roomInfor = Room
                            .builder()
                            .roomId(roomID)
                            .hostelId(hostelId)
                            .roomNumber(roomNumber)
                            .roomArea(roomArea)
                            .capacity(capacity)
                            .hasAttic(hasAttic)
                            .roomStatus(roomStatus)
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return roomInfor;
    }
}
