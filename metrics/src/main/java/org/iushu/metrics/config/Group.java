package org.iushu.metrics.config;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * Metrics Group
 * A Group in Metrics contains a unique group name, a target class and it's members.
 * An group is corresponding a Java Class.
 *
 * Created by iuShu on 19-1-14
 */
public class Group {

    private String groupName;
    private String clazzName;
    private Class<?> clazz;
    private Map<String, Member> members = Maps.newHashMap();

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
        loadClass();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Map<String, Member> getMembers() {
        return Collections.unmodifiableMap(members);
    }

    public void setMembers(Map<String, Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        this.members.put(member.getMemberName(), member);
    }

    public Member getMember(String memberName) {
        return members.get(memberName);
    }

    private void loadClass() {
        try {
            this.clazz = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
