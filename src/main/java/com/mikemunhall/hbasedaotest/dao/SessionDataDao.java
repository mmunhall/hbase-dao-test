package com.mikemunhall.hbasedaotest.dao;

import com.mikemunhall.hbasedaotest.domain.SessionData;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

@Repository
public class SessionDataDao {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private String tableName = "sessionData";
    private byte[] cfSessionData = Bytes.toBytes("sd");
    private byte[] qIdentity = Bytes.toBytes("identity");
    private byte[] qPlatform = Bytes.toBytes("platform");
    private byte[] qProviderId = Bytes.toBytes("providerId");
    private byte[] qDatetime = Bytes.toBytes("datetime");

    public SessionDataDao() { }

    public void save(SessionData sd) {
        hbaseTemplate.execute(tableName, new TableCallback<SessionData>() {
            public SessionData doInTable(HTableInterface table) throws Throwable {
                Put p = new Put(Bytes.toBytes(sd.getSessionId()));
                p.add(cfSessionData, qIdentity, Bytes.toBytes(sd.getIdentity()));
                p.add(cfSessionData, qPlatform, Bytes.toBytes(sd.getPlatform()));
                p.add(cfSessionData, qProviderId, Bytes.toBytes(sd.getProviderId()));
                table.put(p);
                return sd;
            }
        });
    }

}
