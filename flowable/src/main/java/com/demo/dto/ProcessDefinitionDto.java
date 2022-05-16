package com.demo.dto;

import org.flowable.common.engine.impl.db.SuspensionState;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.bpmn.data.IOSpecification;
import org.flowable.identitylink.service.impl.persistence.entity.IdentityLinkEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessDefinitionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String name;
    protected String localizedName;
    protected String description;
    protected String localizedDescription;
    protected String key;
    protected int version;
    protected String category;
    protected String deploymentId;
    protected String resourceName;
    protected String tenantId = ProcessEngineConfiguration.NO_TENANT_ID;
    protected Integer historyLevel;
    protected String diagramResourceName;
    protected boolean isGraphicalNotationDefined;
    protected boolean hasStartFormKey;
    protected int suspensionState = SuspensionState.ACTIVE.getStateCode();
    protected boolean isIdentityLinksInitialized;
    protected IOSpecification ioSpecification;
    protected String derivedFrom;
    protected String derivedFromRoot;
    protected int derivedVersion;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalizedDescription() {
        return localizedDescription;
    }

    public void setLocalizedDescription(String localizedDescription) {
        this.localizedDescription = localizedDescription;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getHistoryLevel() {
        return historyLevel;
    }

    public void setHistoryLevel(Integer historyLevel) {
        this.historyLevel = historyLevel;
    }

    public String getDiagramResourceName() {
        return diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
    }

    public boolean isGraphicalNotationDefined() {
        return isGraphicalNotationDefined;
    }

    public void setGraphicalNotationDefined(boolean graphicalNotationDefined) {
        isGraphicalNotationDefined = graphicalNotationDefined;
    }

    public boolean isHasStartFormKey() {
        return hasStartFormKey;
    }

    public void setHasStartFormKey(boolean hasStartFormKey) {
        this.hasStartFormKey = hasStartFormKey;
    }

    public int getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(int suspensionState) {
        this.suspensionState = suspensionState;
    }

    public boolean isIdentityLinksInitialized() {
        return isIdentityLinksInitialized;
    }

    public void setIdentityLinksInitialized(boolean identityLinksInitialized) {
        isIdentityLinksInitialized = identityLinksInitialized;
    }

    public IOSpecification getIoSpecification() {
        return ioSpecification;
    }

    public void setIoSpecification(IOSpecification ioSpecification) {
        this.ioSpecification = ioSpecification;
    }

    public String getDerivedFrom() {
        return derivedFrom;
    }

    public void setDerivedFrom(String derivedFrom) {
        this.derivedFrom = derivedFrom;
    }

    public String getDerivedFromRoot() {
        return derivedFromRoot;
    }

    public void setDerivedFromRoot(String derivedFromRoot) {
        this.derivedFromRoot = derivedFromRoot;
    }

    public int getDerivedVersion() {
        return derivedVersion;
    }

    public void setDerivedVersion(int derivedVersion) {
        this.derivedVersion = derivedVersion;
    }
}
