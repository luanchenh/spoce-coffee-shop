/**
 *
 */
package KhachHang;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QLMemberCard {
    private ArrayList<MemberCard> listMemberCard;

    public QLMemberCard() {
        this.listMemberCard = new ArrayList();
    }


    public QLMemberCard(ArrayList<MemberCard> listMemberCard) {
        this.listMemberCard = listMemberCard;
    }

    public boolean addMemberCard(MemberCard memberCard) {
        return this.listMemberCard.add(memberCard);
    }

    public boolean removeMemberCard(MemberCard memberCard) {
        return this.listMemberCard.remove(memberCard);
    }
}
