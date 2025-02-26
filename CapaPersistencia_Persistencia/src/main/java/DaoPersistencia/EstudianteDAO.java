package DaoPersistencia;

import ConexionPersistencia.ConexionBDPersistencia;
import DTObject.EstudianteDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Estudiante, utilizando JDBC.
 * Se asume que la tabla Estudiante tiene columnas:
 * identificacion (PRIMARY KEY), nombres, apellidos, edad, carrera_id (FK a Carrera).
 */
public class EstudianteDAO {

    public void insertarEstudiante(EstudianteDto estudianteDTO) {
        String sql = "INSERT INTO Estudiante (identificacion, nombres, apellidos, edad, carrera_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, estudianteDTO.getIdentificacion());
            pstmt.setString(2, estudianteDTO.getNombres());
            pstmt.setString(3, estudianteDTO.getApellidos());
            pstmt.setInt(4, estudianteDTO.getEdad());
            pstmt.setInt(5, estudianteDTO.getCarreraId());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Estudiante insertado correctamente.");
            } else {
                System.err.println("❌ Error al insertar estudiante: no se insertó ninguna fila.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar estudiante: " + e.getMessage());
        }
    }

    public List<EstudianteDto> obtenerTodosLosEstudiantes() {
        List<EstudianteDto> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT identificacion, nombres, apellidos, edad, carrera_id FROM Estudiante";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                EstudianteDto estudianteDTO = new EstudianteDto(
                        rs.getLong("identificacion"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getInt("edad"),
                        rs.getInt("carrera_id")
                );
                listaEstudiantes.add(estudianteDTO);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener estudiantes: " + e.getMessage());
        }

        return listaEstudiantes;
    }

    public EstudianteDto obtenerEstudiantePorId(Long identificacion) {
        EstudianteDto estudianteDTO = null;
        String sql = "SELECT identificacion, nombres, apellidos, edad, carrera_id FROM Estudiante WHERE identificacion = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, identificacion);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    estudianteDTO = new EstudianteDto(
                            rs.getLong("identificacion"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getInt("edad"),
                            rs.getInt("carrera_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener estudiante: " + e.getMessage());
        }

        return estudianteDTO;
    }

    public void actualizarEstudiante(EstudianteDto estudianteDTO) {
        String sql = "UPDATE Estudiante SET nombres = ?, apellidos = ?, edad = ?, carrera_id = ? WHERE identificacion = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudianteDTO.getNombres());
            pstmt.setString(2, estudianteDTO.getApellidos());
            pstmt.setInt(3, estudianteDTO.getEdad());
            pstmt.setInt(4, estudianteDTO.getCarreraId());
            pstmt.setLong(5, estudianteDTO.getIdentificacion());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Estudiante actualizado correctamente.");
            } else {
                System.err.println("❌ No se encontró el estudiante con ID " + estudianteDTO.getIdentificacion());
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar estudiante: " + e.getMessage());
        }
    }

    public void eliminarEstudiante(Long identificacion) {
        String sql = "DELETE FROM Estudiante WHERE identificacion = ?";

        try (Connection conn = ConexionBDPersistencia.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, identificacion);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Estudiante eliminado correctamente.");
            } else {
                System.err.println("❌ No se encontró el estudiante con ID " + identificacion);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar estudiante: " + e.getMessage());
        }
    }
}
