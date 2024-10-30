package com.multipjt.multi_pjt.crew.domain.crew;

import lombok.Data;

@Data
public class CrewMemberResponseDTO {
    private int crew_id;
    private int member_id;
    private String nickname;
    private String profile_region;
    private int profile_age;
    private int battle_wins;
    private int crew_member_state;

    private String badge_level;
    private String badgeImagePath;

    public String getBadgeImagePath() {
        if (badgeImagePath == null) {
            if (badge_level == null) {
                badgeImagePath = "img/crewBadge/CrewBadgeDefault.png";
            } else {
                switch (badge_level) {
                    case "기본":
                        badgeImagePath = "img/crewBadge/CrewBadgeDefault.png";
                        break;
                    case "브론즈":
                        badgeImagePath = "img/crewBadge/CrewBadgeBronze.png";
                        break;
                    case "실버":
                        badgeImagePath = "img/crewBadge/CrewBadgeSilver.png";
                        break;
                    case "골드":
                        badgeImagePath = "img/crewBadge/CrewBadgeGold.png";
                        break;
                    case "플래티넘":
                        badgeImagePath = "img/crewBadge/CrewBadgePlatinum.png";
                        break;
                    case "다이아":
                        badgeImagePath = "img/crewBadge/CrewBadgeDiamond.png";
                        break;
                    default:
                        badgeImagePath = "img/crewBadge/CrewBadgeDefault.png";
                }
            }
        }
        return badgeImagePath;
    }
}
