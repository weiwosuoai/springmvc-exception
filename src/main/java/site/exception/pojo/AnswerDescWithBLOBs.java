package site.exception.pojo;

public class AnswerDescWithBLOBs extends AnswerDesc {
    private String description;

    private String descriptionCh;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDescriptionCh() {
        return descriptionCh;
    }

    public void setDescriptionCh(String descriptionCh) {
        this.descriptionCh = descriptionCh == null ? null : descriptionCh.trim();
    }
}