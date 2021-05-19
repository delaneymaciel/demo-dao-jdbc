package aplication;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			Date date = new Date();
			
			
			
			System.out.println(" *** TEST 1 : Department findById ***");
			
			DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
			Department department = departmentDao.findById(3);
			System.out.println(department);
				
		
			System.out.println("\n *** TEST 3 : Department findAll ***");
			List<Department> list = departmentDao.findAll();
			list = departmentDao.findAll();
			for (Department obj : list) {
				System.out.println(obj);
			}
			
			
			System.out.println("\n *** TEST 4 : Department Insert ***");
			Department newDepartment = new Department(null, "Dep " +  date.getTime());
			departmentDao.insert(newDepartment);
			System.out.println("Inserido. New id = " + newDepartment.getId());
	
			
			System.out.println("\n *** TEST 5 : Department update ***");
			department = departmentDao.findById(11);
			department.setName("Teste " + date.getTime());
			departmentDao.update(department);
			System.out.println("Atualização completada.");
			
	
			System.out.println("\n *** TEST 6 : Department delete ***");
			
			System.out.println("\n Informe o id");
			int id = sc.nextInt();
			departmentDao.deleteById(id);
			System.out.println("Delete completed.");
		
		} finally {
			sc.close();
		}

	}

}
