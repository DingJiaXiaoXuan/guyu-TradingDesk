package com.guyu.mapper;

import com.guyu.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeMapper {

    /**
     * 添加公告
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    int AddNotice(Notice notice);

    /**
     * 分页查询
     * @param start
     * @param point
     * @return List<Notice>
     */
    List<Notice>  QueryNotice(@Param("start") Integer start, @Param("point") Integer point);

    /**
     * 删除店铺信息
     * @param notice_id
     * @return 状态码:1,成功;0,失败;
     */
    int DelNotice(Integer notice_id);

    /**
     * 修改公告
     * @param notice
     * @return 状态码:1,成功;0,失败;
     */
    int updateNotice(Notice notice);

    /**
     * 更新时间
     * @param notice_id
     */
    void UpdateTime(Integer notice_id);

    /**
     * 发布时间
     * @param notice_id
     */
    int publish_time(Integer notice_id);

    /**
     * 查询公告信息总条数
     * @return 总条数
     */
    Integer Total();

    /**
     * 修改公告状态
     * @param notice_id
     * @param notice_status
     * @return 状态:1.成功;0.失败
     */
    int UpdateStatus(@Param("notice_status") int notice_status,@Param("notice_id") Integer notice_id);
}
