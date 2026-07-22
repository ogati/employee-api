-- Insert sample data without IDs
INSERT INTO department (name) VALUES
    ('IT'),
    ('HR'),
    ('Finance');

INSERT INTO employee (name, salary, department_id) VALUES
    ('Alice', 90000, 1),
    ('Bob', 75000, 1),
    ('Carol', 65000, 2),
    ('David', 85000, 3);
