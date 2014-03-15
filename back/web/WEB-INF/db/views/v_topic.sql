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
    u.name,
    COUNT(c.parent_publication_id) AS comment_count
  FROM topic t 
    INNER JOIN publication p ON t.publication_id = p.id
    LEFT JOIN users u ON p.user_id = u.id
    LEFT JOIN comment c ON c.parent_publication_id = p.id
  GROUP  BY t.title, t.subject_id, p.id, u.name
  ORDER BY p.date_posted DESC
);