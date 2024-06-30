package hyunzinbak.core.discount;

import hyunzinbak.core.member.Grade;
import hyunzinbak.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; //1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // enum 은 == 쓰는 게 맞음
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
