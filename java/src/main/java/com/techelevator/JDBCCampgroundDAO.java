package com.techelevator;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCampgroundDAO implements CampgroundDAOInterface {

	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource;
	LocalDate localdate;

	public JDBCCampgroundDAO(DataSource ds) {
		this.dataSource = ds;
	}

	@Override
	public List<Campground> getAllCampgrounds() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Campground> campgroundList = new ArrayList<Campground>();
		String sqlCampground = "SELECT * FROM campground";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCampground);
		while (results.next()) {

			Campground campground = mapRowToCampground(results);

			campgroundList.add(campground);

		}

		
		return campgroundList;
	}

	private Campground mapRowToCampground(SqlRowSet results) {
		Campground campground;
		campground = new Campground();

		campground.setCampgroundId(results.getLong("campground_id"));
		campground.setParkId(results.getLong("park_id"));
		campground.setName(results.getString("name"));
		campground.setOpenFrom(results.getInt("open_from_mm"));
		campground.setOpenTo(results.getInt("open_to_mm"));
		campground.setFee(results.getDouble("daily_fee"));
		return campground;

	}

	public String toString(Campground campground) {

		String campDetailsString = campground.getName() + "\t" + "  " + Month.of(campground.getOpenFrom()) + "  "
				+ Month.of(campground.getOpenTo()) + "  " + campground.getFee();
		return campDetailsString;

	}

}
