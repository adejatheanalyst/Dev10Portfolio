## Field Agent API Test Plan
# An agent has a one-to-many relationship with aliases. 
# An alias is an assumed identity. 
# It's a name and optional persona (description of the identity) under which the agent operates. 
# Aliases aren't required. Some agents have many.
# The alias table exists in the database, but there's no accompanying Java code. 
# Implement the classes and methods required to support aliases in the REST API. Consider your options. 
# Aliases are not independent. They are attached to an agent.

# #API users should be able to:

-[W] Fetch an individual agent with aliases attached.
-[x] Add an alias.
-[x] Update an alias.
-[x] Delete an alias. (No strategy required. An alias is never referenced elsewhere.)
## Domain Rules
-[x] Name is required.
-[x] Persona is not required unless a name is duplicated. The persona differentiates between duplicate names

## Features to complete:

-[W] Global error handler test: Go into your Agency repository and break the sql string (for example, change select to selecttt). 
Open agency.http in REST client, GET {{url}}. The error response should come from the global handler, and not be a BadGrammarException.

# Alias CRUD:

-[x] POST /api/alias with json body that includes name: "Test" and agentId: [existingAgentId]. 
    (Requires an existing agent, use your production-schema or create an initial-data.sql file to make sure there is an existing agent.) 
    Response is 201 and contains json of newly-created alias.

-[x] POST /api/alias with json body that includes name: "" and no agentId. 
    Response is 400, contains error messages "Name is required" and "agentId is required"

-[x] POST /api/alias with json body that includes name: "Test", agentId: [existingAgentId], and no persona. 
    Response is 400, contains error message "Persona is required if name is duplicate"

-[x] POST /api/alias with json body that includes name: "Test", persona: "Test Persona", and agentId: [existingAgentId]. 
    Response is 201, contains json of newly-created alias.

-[x] GET /api/agent/[existingAgentId]. Response is 200, contains agent data including field of list of 2 aliases. 
    Each alias has a name, persona, and agentId.

-[x] PUT /api/alias/2 (or other existingAliasId from final POST request) with json body of aliasId: 
[existingAliasId], name "Test 2", persona of "Test Persona 2", and agentId: [existingAgentId]. 
    Response is 204. GET /api/agent/[existingAgentId], confirm changes to this alias.

-[x] PUT /api/alias/2 (or other existingAliasId from final POST request) with json body of aliasId: 
    existingAliasId, name "", and no agentId. Response is 400, contains error messages "Name is required" and "agentId is required".

-[x] PUT /api/alias/2 (or other existingAliasId from final POST request) with json body of aliasId:
    existingAliasId, name: "Test", persona of "", and agentId: [existingAgentId]. 
    Response is 400, contains error message "Persona is required if name is duplicate".

-[x] PUT /api/alias/2 with json body of aliasId: 999, name: "Test, agentId: [existingAgentId].
    Response is 409.

-[x] PUT /api/alias/999 with json body. Response is 404.

-[x] DELETE /api/alias/2, response is 204. GET /api/agent/[existingAgentId] to confirm deletion.

-[x] DELETE /api/alias/999, response is 404.

