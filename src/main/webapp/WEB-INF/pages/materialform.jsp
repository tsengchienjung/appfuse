<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="materialDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='materialDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="materialList.material"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="materialDetail.heading"/></h2>
    <fmt:message key="materialDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="material" method="post" action="materialform" cssClass="well form-horizontal"
           id="materialForm" onsubmit="return validateMaterial(this)">
<form:hidden path="id"/>
    <spring:bind path="material.content">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="material.content" styleClass="control-label"/>
        <div class="controls">
            <form:input path="content" id="content"  maxlength="255"/>
            <form:errors path="content" cssClass="help-inline"/>
        </div>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select path="creator" items="creatorList" itemLabel="label" itemValue="value"/>
    <spring:bind path="material.mimeType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="material.mimeType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mimeType" id="mimeType"  maxlength="255"/>
            <form:errors path="mimeType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="material.type">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="material.type" styleClass="control-label"/>
        <div class="controls">
            <form:input path="type" id="type"  maxlength="255"/>
            <form:errors path="type" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty material.id}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="material" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['materialForm']).focus();
    });
</script>
