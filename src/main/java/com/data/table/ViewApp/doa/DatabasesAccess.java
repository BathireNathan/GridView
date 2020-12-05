package com.data.table.ViewApp.doa;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DatabasesAccess {
	@Autowired
	JdbcTemplate jdbc;

	@Value("${grid.query}")
	private String query;

	RowMapper<Map<String, Object>> rowMapper = new ColumnMapRowMapper() {
		protected Map<String, Object> createColumnMap(int columnCount) {
			return new LinkedHashMap<>(columnCount);
		}
	};

	public Map<String, Object> fetchUsers() {
		Map<String, Object> dataParsed = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> result = jdbc.query(query, rowMapper);
			System.out.println(result);
			if (result.size() > 0) {
				List<Object> data = result.stream().map(m -> m.values()).collect(Collectors.toList());
				dataParsed.put("header", result.get(0).keySet());
				dataParsed.put("data", data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataParsed;

	}
}
