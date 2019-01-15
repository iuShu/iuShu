package org.iushu.metrics.config;

import com.google.common.base.Preconditions;

import java.lang.reflect.Method;

/**
 * Metrics Member
 * Member is the minimum component unit in Metrics.
 *
 * Created by iuShu on 19-1-14
 */
public class Member {

    /** the owner of member */
    private Group group;

    /** method name by default */
    private String memberName;

    private Method method;
    private String methodName;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
        loadMethod();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Method getMethod() {
        return method;
    }

    public void loadMethod() {
        Class<?> clazz = group.getClazz();
        for (Method method : clazz.getMethods()) {
            if (methodName.equals(method.getName()))
                this.method = method;
        }

        Preconditions.checkArgument(this.method != null, "Unknown method: %s", methodName);
    }
}
