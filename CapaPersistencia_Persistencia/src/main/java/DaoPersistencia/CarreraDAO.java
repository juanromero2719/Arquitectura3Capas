package DaoPersistencia;

import ConexionPersistencia.ConexionBDPersistencia;
import DTObject.CarreraDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Carrera, utilizando JDBC.
 * Cada método ilustra el uso de PreparedStatement y manejo de recursos con try-with-resources.
 */
public class CarreraDAO {

    public void insertarCarrera(CarreraDto carreraDTO) {
        String sql = "INSERT INTO Carrera (nombre, facultad_id) VALUES (?, ?)";
        
        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carreraDTO.getNombre());
            pstmt.setInt(2, carreraDTO.getFacultadId());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Carrera insertada correctamente.");
            } else {
                System.err.println("❌ Error al insertar carrera: no se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar carrera: " + e.getMessage());
        }
    }

    public List<CarreraDto> obtenerTodasLasCarreras() {
        List<CarreraDto> listaCarreras = new ArrayList<>();
        String sql = "SELECT id, nombre, facultad_id FROM Carrera";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CarreraDto carreraDTO = new CarreraDto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("facultad_id")
                );
                listaCarreras.add(carreraDTO);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener carreras: " + e.getMessage());
        }

        return listaCarreras;
    }

    public CarreraDto obtenerCarreraPorId(int id) {
        CarreraDto carreraDTO = null;
        String sql = "SELECT id, nombre, facultad_id FROM Carrera WHERE id = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    carreraDTO = new CarreraDto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("facultad_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener carrera por ID: " + e.getMessage());
        }

        return carreraDTO;
    }

    public void actualizarCarrera(CarreraDto carreraDTO) {
        String sql = "UPDATE Carrera SET nombre = ?, facultad_id = ? WHERE id = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carreraDTO.getNombre());
            pstmt.setInt(2, carreraDTO.getFacultadId());
            pstmt.setInt(3, carreraDTO.getId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Carrera actualizada correctamente.");
            } else {
                System.err.println("❌ No se encontró la carrera con ID " + carreraDTO.getId());
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar carrera: " + e.getMessage());
        }
    }

    public void eliminarCarrera(int id) {
        String sql = "DELETE FROM Carrera WHERE id = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Carrera eliminada correctamente.");
            } else {
                System.err.println("❌ No se encontró la carrera con ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar carrera: " + e.getMessage());
        }
    }
}
