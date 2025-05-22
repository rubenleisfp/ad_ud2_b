package ad.ad_ud2_b.ejemplo_dto.service.dto;

public class FilmDto {

    private String title;
    private short releaseYear;

    public FilmDto() {

    }

    public FilmDto(String title, short releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(short releaseYear) {
        this.releaseYear = releaseYear;
    }
}
