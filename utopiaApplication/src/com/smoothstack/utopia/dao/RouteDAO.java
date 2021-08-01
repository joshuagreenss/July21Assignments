package com.smoothstack.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.utopia.domain.Route;

public class RouteDAO extends DAO<Route> {

	public RouteDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void insert(Route route) throws ClassNotFoundException, SQLException {
		if (route.getId() != null) {
			this.commit("INSERT INTO Route VALUES (?, ?, ?)",
					new Object[] { route.getId(), route.getOrig(), route.getDest() });
		} else {
			commit("INSERT INTO Route (origin_id, destination_id) VALUES (?,?)",
					new Object[] { route.getOrig(), route.getDest() });
		}
	}

	public void update(Route route) throws ClassNotFoundException, SQLException {
		this.commit("UPDATE Route SET origin_id = ?, destination_id = ? WHERE id = ?",
				new Object[] { route.getOrig(), route.getDest(), route.getId() });
	}

	public void delete(Route route) throws ClassNotFoundException, SQLException {
		this.commit("DELETE FROM Route WHERE id = ?", new Object[] { route.getId() });
	}

	@Override
	protected List<Route> listData(ResultSet rs) throws ClassNotFoundException, SQLException {
		if (rs != null) {
			List<Route> results = new ArrayList<>();
			Route item = new Route();
			while (rs.next()) {
				item.setId(rs.getInt(1));
				item.setOrig(rs.getString(2));
				item.setDest(rs.getString(3));
				results.add(item);
			}
			return results;
		}
		return null;
	}
}
