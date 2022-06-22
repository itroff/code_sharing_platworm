package platform.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "snippets")
@Entity(name = "snippets")
public class Snippet implements Comparable<Snippet>{

    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID id;

    @Column
    private String code = "";

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column
    private LocalDateTime date;

    @Column
    private long views;

    @Column
    private long time;

    @Column(name = "views_commit")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long viewsCommit;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private boolean timeRestrict = false;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private boolean viewsRestrict = false;

    public Snippet(LocalDateTime date) {
        this.date = date;
    }


    public Snippet(String code, LocalDateTime date, long views, long time) {
        this.code = code;
        this.date = date;
        this.views = views;
        this.time = time;
    }

    public void incrementViews(){
        viewsCommit++;
    }

    @Override
    public int compareTo(Snippet o) {
        return date.compareTo(o.getDate());
    }

    public String formattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
