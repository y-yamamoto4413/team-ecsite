SET foreign_key_checks=1;

USE teamdb;

INSERT INTO mst_user
(user_name, password, family_name, first_name, family_name_kana, first_name_kana, gender)
VALUES ('taro@gmail.com', '111111', '山田', '太郎', 'やまだ', 'たろう', 0);

INSERT INTO mst_category (category_name,category_description) VALUES
('リビング', 'リビングのカテゴリーです'),
('キッチン', 'キッチンのカテゴリーです'),
('ベッドルーム','ベッドルームのカテゴリーです');

INSERT INTO mst_product(product_name,product_name_kana,product_description,category_id,price,image_full_path,release_date,release_company) VALUES 
('テーブル','てーぶる','機能美を追求したデザインで、リビングに洗練された印象をもたらすテーブル。',1,60000,'/img/table.jpg','2024/10/05','Architect Table'),
('ソファ','そふぁ','上質な素材とデザインが、くつろぎの時間を特別なひとときへと昇華させるソファ。',1,85000,'/img/sofa.jpg','2024/11/05','Luxe Comfort'),
('プレート','ぷれーと','料理をアートのように引き立て、食卓に彩りを添えるプレート。',2,2000,'/img/plate.jpg','2024/12/05','Culinary Canvas'),
('コップ','こっぷ','毎日使うものだからこそ、デザインと機能性にこだわった特別なコップ。',2,1200,'/img/cup.jpg','2025/01/05','Fluid Design'),
('ベッド','べっど','質の高い睡眠を追求し、心身ともにリフレッシュできる安らぎのベッド。',3,120000,'/img/bed.jpg','2025/02/05','Somnus Core'),
('ランプ','らんぷ','柔らかな光とデザインが、一日の疲れを癒し心地よい眠りへ誘うランプ。',3,15000,'/img/lamp.jpg','2025/03/05','Luminous Atelier');
