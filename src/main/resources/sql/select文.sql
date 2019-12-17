SELECT
  j2.human_id,
  j2.emp_id,
  j2.human_name,
  j2.join_date,
  j2.icon_img,
  j2.assign_company_name,
  j2.skill_id,
  j2.skill_name,
  j2.score,
  j2.skill_type,
  j2.description,
  j2.order_status,
  j2.order_id,
  j2.order_ver
FROM (
    SELECT
      human_id,
      emp_id,
      human_name,
      join_date,
      icon_img,
      assign_company_name,
      skill_id,
      skill_name,
      score,
      skill_type,
      description,
      order_status,
      order_id,
      rank() over(
        partition by human_id
        ORDER BY
          order_id DESC
      ) AS order_ver
    FROM (
        (
          SELECT
            human_id,
            emp_id,
            human_name,
            join_date,
            icon_img,
            assign_company_name,
            base_skill_id AS skill_id,
            base_skill_name AS skill_name,
            base_skill_score AS score,
            'base' AS skill_type,
            '' AS description,
            order_status,
            order_id
          FROM humans
          LEFT JOIN orders USING(human_id)
          LEFT JOIN pre_human_base_skills USING(order_id)
          LEFT JOIN base_skills USING(base_skill_id)
        )
        UNION
          (
            SELECT
              human_id,
              emp_id,
              human_name,
              join_date,
              icon_img,
              assign_company_name,
              common_skill_id AS skill_id,
              common_skill_name AS skill_name,
              common_skill_score AS score,
              'common' AS skill_type,
              description,
              order_status,
              order_id
            FROM humans
            LEFT JOIN orders USING(human_id)
            LEFT JOIN pre_human_common_skills USING(order_id)
            LEFT JOIN common_skills USING(common_skill_id)
          )
        UNION
          (
            SELECT
              human_id,
              emp_id,
              human_name,
              join_date,
              icon_img,
              assign_company_name,
              sub_skill_id AS skill_id,
              sub_skill_name AS skill_name,
              sub_skill_status_type AS score,
              'sub' AS skill_type,
              description,
              order_status,
              order_id
            FROM humans
            LEFT JOIN orders USING(human_id)
            LEFT JOIN pre_human_sub_skills USING(order_id)
            LEFT JOIN sub_skills USING(sub_skill_id)
          )
      ) AS j
  ) AS j2
WHERE
  order_ver = 1
  AND human_id =:humanId
ORDER BY
  human_id,
  skill_type,
  skill_id;