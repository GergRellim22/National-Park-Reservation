package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCampgroundDAOTest {

JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
	private static JDBCCampgroundDAO dao;

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
		dao = new JDBCCampgroundDAO(dataSource);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}

	@Test
	public void getAllCampgroundsTest() {
//		List<Campground> campgrounds = dao.getAllCampgrounds();
//		SqlRowSet rowCount = jdbcTemplate.queryForRowSet("SELECT * FROM campground");
//		int rowCountInt = 0;
//		if (rowCount.next()) {
//			rowCountInt = rowCount.getInt("count");
//			
//		}
//		assertEquals(campgrounds.size(), rowCountInt);
		List<Campground> allCampgrounds = dao.getAllCampgrounds();
		int rowCount = 0;
		Campground campground = new Campground();
		campground.setName("Candy Campground");
		dao.getAllCampgrounds();
		List<Campground> newAllCampgounds = dao.getAllCampgrounds();
		assertEquals(allCampgrounds.size(), newAllCampgounds.size());
		
		
		
	}
}
