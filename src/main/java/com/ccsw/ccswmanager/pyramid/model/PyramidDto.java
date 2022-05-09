package com.ccsw.ccswmanager.pyramid.model;

/**
 * @author jchengli
 *
 */

public class PyramidDto {

    private String profile;

    private Long count;

    private Double index;

    public PyramidDto(String profile, Long count, Double index) {
        this.profile = profile;
        this.count = count;
        this.index = index;
    }

    public PyramidDto() {
    }

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
    public Long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Long count) {
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
