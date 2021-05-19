package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public  DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO Department "
					+ "(Name) "
					+ "VALUES "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rowsAfeected = st.executeUpdate();
			
			if (rowsAfeected > 0 ) {
				rs= st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
			
				
			}else {
				throw new DbException("Erro inesperado, nenhuma linha foi inserida.");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("UPDATE Department "
					+ "SET Name = ? "
					+ "WHERE Id = ?",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			int rowsAfeected = st.executeUpdate();
			System.out.println("Linhas atualada: " +  rowsAfeected);
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM Department where id =?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * "
					+ "FROM Department "
					+ "WHERE id = ? "
					+ "ORDER BY Name");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				
			
				return dep;
				
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeStatement(st);
		}
		
		
		
	}

	private Department instantiateDepartment(ResultSet rs, Department dep) throws SQLException {
		Department obj = new Department();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep =  new Department();
		dep.setId(rs.getInt("id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * "
					+ "FROM Department "
					+ "ORDER BY Name");

			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			Map <Integer, Department> map = new HashMap<>();
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("id"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					dep = map.put(rs.getInt("id"),dep);
				}
				
				Department obj = instantiateDepartment(rs, dep);
				list.add(obj);
			}
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeStatement(st);
		}
	}


}
