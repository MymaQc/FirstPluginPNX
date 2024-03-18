package myma.utils.enums;

public enum Rank {

    JOUEUR("joueur"),
    VIP("vip"),
    YT("yt"),
    GUIDE("guide"),
    MODO("modo"),
    ADMIN("admin"),
    FONDA("fonda");

    private final String rank;

    Rank(String rank) {
        this.rank = rank;
    }

    public String toString() {
        return rank;
    }

}
