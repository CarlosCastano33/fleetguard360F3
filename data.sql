INSERT INTO routes (id, name, price)
VALUES
    ('76d2ffdd-7c48-49ec-bd61-f6ca05f12269', 'Medellín - Manizales (06:00)', 71000)
ON CONFLICT (id) DO UPDATE
SET name = EXCLUDED.name, price = EXCLUDED.price;

INSERT INTO route_stops (id, stop_name, first_stop, last_stop, stop_order, time_in_stop, route_id)
VALUES
    ('de2441ea-c104-4018-88f9-f07ab317b966', 'Medellín', true, false, 1, '06:00', '76d2ffdd-7c48-49ec-bd61-f6ca05f12269'),
    ('d93837b8-a433-4d46-ac99-9184f2a5fc3a', 'Santa Bárbara', false, false, 2, null, '76d2ffdd-7c48-49ec-bd61-f6ca05f12269'),
    ('4e493947-510d-4423-970e-8142b366d4f9', 'La Pintada', false, false, 3, null, '76d2ffdd-7c48-49ec-bd61-f6ca05f12269'),
    ('0884d2e4-d496-4912-8380-19d01766c967', 'Chinchiná', false, false, 4, null, '76d2ffdd-7c48-49ec-bd61-f6ca05f12269'),
    ('565b7183-457d-43bb-b81e-d04d291bed63', 'Manizales', false, true, 5, '10:50', '76d2ffdd-7c48-49ec-bd61-f6ca05f12269')
ON CONFLICT (id) DO UPDATE
SET stop_name = EXCLUDED.stop_name,
    first_stop = EXCLUDED.first_stop,
    last_stop = EXCLUDED.last_stop,
    stop_order = EXCLUDED.stop_order,
    time_in_stop = EXCLUDED.time_in_stop,
    route_id = EXCLUDED.route_id;

INSERT INTO routes (id, name, price)
VALUES
    ('d542c32a-60c2-42a6-af40-34b10e0cb3a3', 'Medellín - Armenia (07:00)', 78000),
    ('568f4ed5-99f1-4930-9c54-81b9d6becc14', 'Medellín - Armenia (11:00)', 78000),
    ('3414bacc-28a6-4bc1-bddd-e1c6a1d8692b', 'Medellín - Armenia (14:00)', 78000)
ON CONFLICT (id) DO UPDATE
    SET name = EXCLUDED.name, price = EXCLUDED.price;

INSERT INTO route_stops (id, stop_name, first_stop, last_stop, stop_order, time_in_stop, route_id)
VALUES
    ('be7e20c8-84b0-4d3d-8558-93671884d7f9', 'Medellín', true, false, 1, '07:00', 'd542c32a-60c2-42a6-af40-34b10e0cb3a3'),
    ('363a5d06-d263-4526-ad4a-8a2e33546188', 'La Pintada', false, false, 2, null, 'd542c32a-60c2-42a6-af40-34b10e0cb3a3'),
    ('4bfaa82b-fb91-4a6f-b1cd-dd2e8369c6ad', 'La Tebaida', false, false, 3, null, 'd542c32a-60c2-42a6-af40-34b10e0cb3a3'),
    ('6c2f6b56-ff22-4c07-b3b2-3662cf9aebcd', 'Armenia', false, true, 4, '12:30', 'd542c32a-60c2-42a6-af40-34b10e0cb3a3'),

    ('1449dee1-087f-46a2-985b-a1311fed1b01', 'Medellín', true, false, 1, '11:00', '568f4ed5-99f1-4930-9c54-81b9d6becc14'),
    ('bcf5d57c-5742-4ad2-a758-ab0478a26d16', 'La Pintada', false, false, 2, null, '568f4ed5-99f1-4930-9c54-81b9d6becc14'),
    ('15edfc44-f5c1-409f-85ba-940968daaebb', 'La Tebaida', false, false, 3, null, '568f4ed5-99f1-4930-9c54-81b9d6becc14'),
    ('1b788194-7bf6-4d92-9017-6e8d2f09bd5e', 'Armenia', false, true, 4, '16:30', '568f4ed5-99f1-4930-9c54-81b9d6becc14'),

    ('94d99e02-2018-4c9d-bfb3-8e8f2ae9990a', 'Medellín', true, false, 1, '14:00', '3414bacc-28a6-4bc1-bddd-e1c6a1d8692b'),
    ('1c7ebb66-354c-44a9-8773-6be4098a3c8d', 'La Pintada', false, false, 2, null, '3414bacc-28a6-4bc1-bddd-e1c6a1d8692b'),
    ('5fa790ee-5535-432f-adde-c399f7c1859c', 'La Tebaida', false, false, 3, null, '3414bacc-28a6-4bc1-bddd-e1c6a1d8692b'),
    ('12cf4e24-c761-4bb5-a57f-4d7705c23222', 'Armenia', false, true, 4, '19:30', '3414bacc-28a6-4bc1-bddd-e1c6a1d8692b')
ON CONFLICT (id) DO UPDATE
    SET stop_name = EXCLUDED.stop_name,
        first_stop = EXCLUDED.first_stop,
        last_stop = EXCLUDED.last_stop,
        stop_order = EXCLUDED.stop_order,
        time_in_stop = EXCLUDED.time_in_stop,
        route_id = EXCLUDED.route_id;