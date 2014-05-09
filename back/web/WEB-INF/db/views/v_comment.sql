CREATE OR REPLACE VIEW v_comment AS (
  SELECT
    p.id,
    p.text_plain,
    p.text_html,
    p.anonymous,
    p.user_id,
    p.date_posted,
    u.username,
    COALESCE(SUM(v.value), 0) AS score,
    c.parent_publication_id
  FROM comment c
    INNER JOIN publication p ON c.publication_id = p.id
    LEFT JOIN users u ON p.user_id = u.id
    LEFT JOIN vote v ON v.publication_id = p.id
  WHERE p.sys_deleted = FALSE
  GROUP  BY p.id, u.username, c.parent_publication_id
  ORDER BY p.date_posted DESC
);