@Aggregate
public class CpaAggregate {

	@AggregateIdentifier
	private String externalId;

	@AggregateMember
	private Laboratory laboratory;

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

}

public class Laboratory extends Entity {

  private OperatingSystem operatingSystem;

  @CommandHandler
	public void handle(CreateIdentifierLABCommand cmd) {
		AggregateLifecycle.apply(new IdentifierCreatedEvent(cmd.externalId, cmd.am));
	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

}

public class Entity {

	@EntityId
	private String entityId;

	private Set<Identifier> identifiers = new HashSet<>();

	@EventSourcingHandler
	public void on(IdentifierCreatedEvent evt) {
		this.entityId = evt.externalId;
		this.identifiers.add(evt.identifiant);
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Set<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

}
