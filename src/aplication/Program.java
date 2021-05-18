package aplication;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		//Department obj = new Department(1,"Books");
		//System.out.println(obj);
		
	//	Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(" *** TEST 1 : Seller findById ***");
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n *** TEST 2 : Seller findById ***");
		Department department = new Department(2,null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n *** TEST 3 : Seller findAll ***");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n *** TEST 4 : Seller Insert ***");
		Seller newSeller = new Seller(null, "fred", "fred@gmail.com.", new Date(),4000.0, department );
		sellerDao.insert(newSeller);
		System.out.println("Inserido. New id = " + newSeller.getId());

		System.out.println("\n *** TEST 5 : Seller update ***");
		seller = sellerDao.findById(1);
		sellerDao.update(seller);
		System.out.println("Atualização completada.");
		
		System.out.println("\n *** TEST 6 : Seller delete ***");
		
		System.out.println("\n Informe o id");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed.");
		
		
	}

}
