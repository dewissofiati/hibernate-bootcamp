package tdi.bootcamp.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
public class BaseEmbed {
    @Column(name = "data_embed", length = 30)
    private String dataEmbed;

    @Column(name = "tgl_update")
    private Timestamp tglUpdate;

    public Timestamp getTglUpdate() {
        return tglUpdate;
    }

    public void setTglUpdate(Timestamp tglUpdate) {
        this.tglUpdate = tglUpdate;
    }

    public String getDataEmbed() {
        return dataEmbed;
    }

    public void setDataEmbed(String dataEmbed) {
        this.dataEmbed = dataEmbed;
    }
}
