package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;

import java.util.List;

public interface SecurityClearanceRepository {
    SecurityClearance findById(int securityClearanceId);
    List<SecurityClearance> findAll();
    List<SecurityClearance> findActive();
    SecurityClearance add(SecurityClearance securityClearance);
    boolean update(SecurityClearance securityClearance);
    boolean deleteById(int securityClearance);
}
