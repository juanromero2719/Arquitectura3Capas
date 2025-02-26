package DaoPersistencia;

import ConexionPersistencia.ConexionBDPersistencia;
import DTObject.FacultadDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Facultad, utilizando JDBC.
 * Se asume que la tabla Facultad tiene columnas:
 * id (PRIMARY KEY autoincrement), nombre (VARCHAR).
 */
public class FacultadDAO {

    public void insertarFacultad(FacultadDto facultadDTO) {
        String sql = "INSERT INTO Facultad (nombre) VALUES (?)";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, facultadDTO.getNombre());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Facultad insertada correctamente.");
            } else {
                System.err.println("❌ Error al insertar facultad: no se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar facultad: " + e.getMessage());
        }
    }

    public List<FacultadDto> obtenerTodasLasFacultades() {
        List<FacultadDto> listaFacultades = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Facultad";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                FacultadDto facultadDTO = new FacultadDto(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                listaFacultades.add(facultadDTO);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener facultades: " + e.getMessage());
        }

        return listaFacultades;
    }

    public FacultadDto obtenerFacultadPorId(int id) {
        FacultadDto facultadDTO = null;
        String sql = "SELECT id, nombre FROM Facultad WHERE id = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    facultadDTO = new FacultadDto(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener facultad por ID: " + e.getMessage());
        }

        return facultadDTO;
    }

    public void actualizarFacultad(FacultadDto facultadDTO) {
        String sql = "UPDATE Facultad SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, facultadDTO.getNombre());
            pstmt.setInt(2, facultadDTO.getId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Facultad actualizada correctamente.");
            } else {
                System.err.println("❌ No se encontró la facultad con ID " + facultadDTO.getId());
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar facultad: " + e.getMessage());
        }
    }

    public void eliminarFacultad(int id) {
        String sql = "DELETE FROM Facultad WHERE id = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Facultad eliminada correctamente.");
            } else {
                System.err.println("❌ No se encontró la facultad con ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar facultad: " + e.getMessage());
        }
    }
}
