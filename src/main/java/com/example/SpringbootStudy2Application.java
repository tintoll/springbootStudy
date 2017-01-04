package com.example;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import com.example.repository.CustomerRepositoryJpa;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;


@ComponentScan
@EnableAutoConfiguration
public class SpringbootStudy2Application implements CommandLineRunner{

	/*
	@Autowired
	CustomerService customerService;
	@Override
	public void run(String... strings) throws Exception {

		//데이터 추가
		customerService.save(new Customer(1,"Nobita","Nobi"));
		customerService.save(new Customer(2,"Takeshi","Goda"));
		customerService.save(new Customer(3,"Suneo","HoneKawa"));

		//데이터 표시
		customerService.finAll().forEach(System.out::println);
	}
	*/


	public static void main(String[] args) {
		SpringApplication.run(SpringbootStudy2Application.class, args);
	}

	/*
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	CustomerRepository customerRepository;
	*/

	@Autowired
	CustomerRepositoryJpa customerRepository;

	@Override
	public void run(String... strings) throws Exception {

		// 데이터 추가
		Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		System.out.println(created + " is created!");

		//페이징 처리
		Pageable pageable = new PageRequest(0,3);
		Page<Customer> page = customerRepository.findAllOrderByName(pageable);

		System.out.println("한 페이지당 데이터 수 : "+page.getSize());
		System.out.println("현재 페이지  : "+page.getNumber());
		System.out.println("전체 페이지  수 : "+page.getTotalPages());
		System.out.println("전체  데이터 수 : "+page.getTotalElements());

		page.getContent().forEach(System.out::println);




		// 데이터 표시
		//customerRepository.findAllOrderByName()
		//		.forEach(System.out::println);

		/*
		String sql = "SELECT :a + :b";
		SqlParameterSource param = new MapSqlParameterSource()
									.addValue("a",100)
									.addValue("b",200);
		Integer result = jdbcTemplate.queryForObject(sql,param,Integer.class);
									*/

		/*
		String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",1);
		*/

		/*
		Customer result = jdbcTemplate.queryForObject(sql, param, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Customer(rs.getInt("id"), rs.getString("first_name")
				, rs.getString("last_name"));
			}
		});
		*/
		/*
		//람다 표현식으로
		Customer result = jdbcTemplate.queryForObject(sql, param,
				(rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("first_name")
						, rs.getString("last_name"))
				);



		System.out.println("result : "+result );
		*/

	}
}
