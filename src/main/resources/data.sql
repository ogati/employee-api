-- Insert sample data without IDs
INSERT INTO department (name) VALUES
    ('IT'),
    ('HR'),
    ('Finance');

INSERT INTO employee (name, salary, department_id) VALUES
    ('Alice', 90000, 1),
    ('Carol', 65000, 2),
    ('Bob', 75000, 1),
    ('David', 85000, 3),
    ('David 2', 85003, 3),
    ('Alice 2', 90003, 1),
    ('Bob 2', 75003, 1),
    ('Carol 2', 65003, 2),
    ('Bob 3', 75005, 1),
    ('Carol 3', 65005, 2),
    ('David 3', 85005, 3),
    ('Alice 3', 90005, 1),
    ('Bob 4', 75008, 1),
    ('Alice 4', 90008, 1),
    ('Carol 4', 65008, 2),
    ('David 4', 85008, 3),
    ('David 5', 85011, 3),
    ('Alice 5', 90011, 1),
    ('Bob 5', 75011, 1),
    ('Carol 5', 65011, 2);
