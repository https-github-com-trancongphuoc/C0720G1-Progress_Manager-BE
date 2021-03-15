package c0720g1.entity;

import javax.persistence.*;

@Entity
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "TEXT")
    String url;
    @ManyToOne
    @JoinColumn(name = "process_id")
    ProcessGraduate processGraduate;

    public FileUpload() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProcessGraduate getProcessGraduate() {
        return processGraduate;
    }

    public void setProcessGraduate(ProcessGraduate processGraduate) {
        this.processGraduate = processGraduate;
    }
}
