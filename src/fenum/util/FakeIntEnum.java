package fenum.util;

import java.util.HashSet;
import java.util.Set;

public class FakeIntEnum {
    private Set<Integer> nums;

    public FakeIntEnum() {
        nums = new HashSet<>();
    }

    public void add(int val) {
        nums.add(val);
    }
    public boolean isMemberOf(int val) {
        return nums.contains(val);
    }
}
