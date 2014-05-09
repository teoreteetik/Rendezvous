CREATE OR REPLACE VIEW v_topic AS (
  SELECT
    p.id,
    t.title,
    p.text_plain,
    p.text_html,
    t.subject_id,
    p.anonymous,
    p.user_id,
    p.date_posted,
    u.username,
    COUNT(c.parent_publication_id) AS comment_count,
    COALESCE(SUM(v.value), 0) AS score
  FROM topic t
    INNER JOIN publication p ON t.publication_id = p.id
    LEFT JOIN users u ON p.user_id = u.id
    LEFT JOIN comment c ON c.parent_publication_id = p.id
    LEFT JOIN vote v ON v.publication_id = p.id
  WHERE p.sys_deleted = FALSE
  GROUP  BY t.title, t.subject_id, p.id, u.username
  ORDER BY p.date_posted DESC
);