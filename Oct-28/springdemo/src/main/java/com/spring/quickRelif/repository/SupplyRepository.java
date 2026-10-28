package com.spring.quickRelif.repository;

import com.spring.quickRelif.entity.Supply;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplyRepository {

    private final JdbcTemplate jdbcTemplate;

    // constructor injection
    public SupplyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // maps each database row to a supply object
    private final RowMapper<Supply> supplyRowMapper = (rs, rowNum) ->
            new Supply(
                    rs.getLong("id"),
                    rs.getString("item_name"),
                    rs.getInt("quantity")
            );

    // fetch all supplies
    public List<Supply> findAll() {
        String sql = "select * from supplies";
        return jdbcTemplate.query(sql, supplyRowMapper);
    }

    // find a supply by its name
    public Supply findByItemName(String itemName) {
        String sql = """
                SELECT id, item_name, quantity 
                FROM supplies
                WHERE LOWER (item_name) = LOWER(?)
                """;

        List<Supply> supplies = jdbcTemplate.query(sql, supplyRowMapper, itemName);

        return supplies.isEmpty() ? null : supplies.get(0);
    }

    // insert a new supply
    public int addSupply(String itemName, int quantity) {
        String sql = """
                INSERT into supplies (item_nmae, qunatity)
                value (?,?)
                """;

        return jdbcTemplate.update(sql, itemName, quantity);
    }

    // Increase quantity of an existing supply
    public int restockSupply(String itemName, int quantity) {
        String sql = """
                UPDATE supplies
                SET quantity = quantity + ?
                WHERE LOWER(item_name) = LOWER(?)
                """;

        return jdbcTemplate.update(sql, quantity, itemName);
    }


}