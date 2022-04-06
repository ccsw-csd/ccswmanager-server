package com.capgemini.ccsw.ccswmanager.pyramid.model;

/**
 * @author jchengli
 *
 */

public class PyramidDto {

    private String profile;

    private int count;

    private Double index;

    /**
     * @return the profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the index
     */
    public Double getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Double index) {
        this.index = index;
    }

}
