package com.demo.dto;

import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.FlowableListener;
import org.flowable.common.engine.impl.db.SuspensionState;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.eventsubscription.service.impl.persistence.entity.EventSubscriptionEntity;
import org.flowable.identitylink.service.impl.persistence.entity.IdentityLinkEntity;
import org.flowable.variable.service.impl.persistence.entity.VariableInstanceEntity;

import java.util.Date;
import java.util.List;

public class ProcessInstanceDto {


    private static final long serialVersionUID = 1L;

    // current position /////////////////////////////////////////////////////////

    protected FlowElement currentFlowElement;
    protected FlowableListener currentListener; // Only set when executing an execution listener

    protected FlowElement originatingCurrentFlowElement;

    /**
     * the process instance. this is the root of the execution tree. the processInstance of a process instance is a self reference.
     */
    protected ExecutionEntityImpl processInstance;

    /** the parent execution */
    protected ExecutionEntityImpl parent;


    /** super execution, not-null if this execution is part of a subprocess */
    protected ExecutionEntityImpl superExecution;

    /** reference to a subprocessinstance, not-null if currently subprocess is started from this execution */
    protected ExecutionEntityImpl subProcessInstance;

    /** The tenant identifier (if any) */
    protected String tenantId = ProcessEngineConfiguration.NO_TENANT_ID;
    protected String name;
    protected String description;
    protected String localizedName;
    protected String localizedDescription;

    protected Date lockTime;
    protected String lockOwner;

    // state/type of execution //////////////////////////////////////////////////

    protected boolean isActive = true;
    protected boolean isScope = true;
    protected boolean isConcurrent;
    protected boolean isEnded;
    protected boolean isEventScope;
    protected boolean isMultiInstanceRoot;
    protected boolean isCountEnabled;

    // events ///////////////////////////////////////////////////////////////////

    // TODO: still needed in v6?

    protected String eventName;


    // cascade deletion ////////////////////////////////////////////////////////

    protected String deleteReason;

    protected int suspensionState = SuspensionState.ACTIVE.getStateCode();

    protected String startActivityId;
    protected String startUserId;
    protected Date startTime;

    // CountingExecutionEntity
    protected int eventSubscriptionCount;
    protected int taskCount;
    protected int jobCount;
    protected int timerJobCount;
    protected int suspendedJobCount;
    protected int deadLetterJobCount;
    protected int externalWorkerJobCount;
    protected int variableCount;
    protected int identityLinkCount;

    /**
     * Persisted reference to the processDefinition.
     *
     * @see #processDefinitionId
     * @see #setProcessDefinitionId(String)
     * @see #getProcessDefinitionId()
     */
    protected String processDefinitionId;

    /**
     * Persisted reference to the process definition key.
     */
    protected String processDefinitionKey;

    /**
     * Persisted reference to the process definition name.
     */
    protected String processDefinitionName;

    /**
     * persisted reference to the process definition version.
     */
    protected Integer processDefinitionVersion;

    /**
     * Persisted reference to the deployment id.
     */
    protected String deploymentId;

    /**
     * Persisted reference to the current position in the diagram within the {@link #processDefinitionId}.
     *
     * @see #activityId
     * @see #setActivityId(String)
     * @see #getActivityId()
     */
    protected String activityId;

    /**
     * The name of the current activity position
     */
    protected String activityName;

    /**
     * Persisted reference to the process instance.
     *
     * @see #getProcessInstance()
     */
    protected String processInstanceId;

    /**
     * Persisted reference to the business key.
     */
    protected String businessKey;

    /**
     * Persisted reference to the business status.
     */
    protected String businessStatus;

    /**
     * Persisted reference to the parent of this execution.
     *
     * @see #getParent()
     * @see #setParentId(String)
     */
    protected String parentId;

    /**
     * Persisted reference to the super execution of this execution
     *
     * @see #getSuperExecution()
     * @see #setSuperExecution(ExecutionEntity)
     */
    protected String superExecutionId;

    protected String rootProcessInstanceId;
    protected ExecutionEntityImpl rootProcessInstance;

    protected boolean forcedUpdate;


    // Callback
    protected String callbackId;
    protected String callbackType;

    // Reference
    protected String referenceId;
    protected String referenceType;

    /**
     * The optional stage instance id, if this execution runs in the context of a CMMN case and has a parent stage it belongs to.
     */
    protected String propagatedStageInstanceId;
}
