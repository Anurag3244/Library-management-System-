public class Member {
    private int memberId;
    private String name;
    private String email;
    private String membershipType;
    private boolean isActive;

    public Member(int memberId, String name, String email, String membershipType, boolean isActive) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.membershipType = membershipType;
        this.isActive = isActive;
    }

    // Getters and setters
    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMembershipType() { return membershipType; }
    public boolean isActive() { return isActive; }

    public void renewMembership() {
        this.isActive = true;
    }
}
