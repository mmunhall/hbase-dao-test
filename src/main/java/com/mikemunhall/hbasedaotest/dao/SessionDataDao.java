package com.mikemunhall.hbasedaotest.dao;

import com.mikemunhall.hbasedaotest.domain.SessionData;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Repository
public class SessionDataDao {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private String tableNameStr = "sessionData";
    private String columnFamily = "sd";
    private byte[] cfSessionData = Bytes.toBytes(columnFamily);
    private byte[] qIdentity = Bytes.toBytes("identity");
    private byte[] qPlatform = Bytes.toBytes("platform");
    private byte[] qProviderId = Bytes.toBytes("providerId");

    private Admin admin;
    private TableName tn;

    public SessionDataDao() { }

    @PostConstruct
    public void init() throws IOException {
        admin = ConnectionFactory.createConnection(hbaseTemplate.getConfiguration()).getAdmin();
        tn = TableName.valueOf(tableNameStr);

        if (!admin.tableExists(tn)) {
            HTableDescriptor tableDescriptor = new HTableDescriptor(tn);
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(cfSessionData);
            tableDescriptor.addFamily(columnDescriptor);
            admin.createTable(tableDescriptor);

            //TODO: Log info message about creating table.
        }
    }

    public void drop() throws IOException {
        if (admin.isTableEnabled(tn)) {
            admin.disableTable(tn);
        }
        admin.deleteTable(tn);
    }

    public void save(SessionData sd) {
        hbaseTemplate.execute(tableNameStr, new TableCallback<Object>() {
            public Object doInTable(HTableInterface table) throws Throwable {
                Put p = new Put(Bytes.toBytes(sd.getSessionId()));
                p.addColumn(cfSessionData, qIdentity, Bytes.toBytes(sd.getIdentity()));
                p.addColumn(cfSessionData, qPlatform, Bytes.toBytes(sd.getPlatform()));
                p.addColumn(cfSessionData, qProviderId, Bytes.toBytes(sd.getProviderId()));
                table.put(p);

                return null;
            }
        });
    }

    public List<SessionData> findAll() {
        Scan scan = new Scan();
        scan.setMaxResultSize(100l); // TODO: Investigate. This setting is ignored on my local standalone installation.

        return hbaseTemplate.find(tableNameStr, scan, new SessionDataRowMapper());
    }

    public SessionData findOne(String sessionId) {
        SessionData sd = hbaseTemplate.get(tableNameStr, sessionId, columnFamily, new SessionDataRowMapper());

        if (sd.getSessionId() == null) {
            sd = null;
        }

        return sd;
    }

    private class SessionDataRowMapper implements RowMapper<SessionData> {
        @Override
        public SessionData mapRow(Result result, int rowNum) throws Exception {
            SessionData sd = new SessionData();
            sd.setSessionId(Bytes.toString(result.getRow()));
            sd.setIdentity(Bytes.toString(result.getValue(cfSessionData, qIdentity)));
            sd.setPlatform(Bytes.toString(result.getValue(cfSessionData, qPlatform)));
            sd.setProviderId(Bytes.toString(result.getValue(cfSessionData, qProviderId)));

            return sd;
        }
    }

}
