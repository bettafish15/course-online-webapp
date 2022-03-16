
alter table tests 
       drop constraint UK1oyupuj94fo4yqwfdcl6yfi41;
      
alter table tests 
       drop constraint UK14e9pu34lv8xrlxp774ctgga8;
      
alter table lectures 
       drop constraint UK205e2wdn267nuwqphqvpb4all;
      
alter table lectures 
       drop constraint UKl37qgxi98qe6j4ah0duhn24he;

alter table lectures 
       add constraint UKl37qgxi98qe6j4ah0duhn24he unique (course_id, week, index_order);

alter table lectures 
       add constraint UK205e2wdn267nuwqphqvpb4all unique (course_id, week, name);

alter table tests 
       add constraint UK14e9pu34lv8xrlxp774ctgga8 unique (course_id, week, index_order);

alter table tests 
       add constraint UK1oyupuj94fo4yqwfdcl6yfi41 unique (course_id, week, name);