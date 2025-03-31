package learn.field_agent.models;

import java.util.Objects;

public class Alias {
    private int aliasId;
    private String name;
    private String persona;
//    private int agentId;
    private Agent agentId;

    public Alias() {
    }

    public Alias(int aliasId, String name, String persona, Agent agentId) {
        this.aliasId = aliasId;
        this.name = name;
        this.persona = persona;
        this.agentId = agentId;
    }

    public Agent getAgent() {
        return agentId;
    }

    public void setAgent(Agent agentId) {
        this.agentId = agentId;
    }

    public int getAliasId() {
        return aliasId;
    }

    public void setAliasId(int aliasId) {
        this.aliasId = aliasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alias alias = (Alias) o;
        return aliasId == alias.aliasId && Objects.equals(name, alias.name) && Objects.equals(persona, alias.persona) && Objects.equals(agentId, alias.agentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aliasId, name, persona, agentId);
    }
}
