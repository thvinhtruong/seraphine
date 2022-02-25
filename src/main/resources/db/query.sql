-- a. What is the age of the athlete with the number 4711?

Select Age from Athlete where ANr = 4711;

-- b. Provide the names of all athletes that hold a world record in at least one competition. 
-- If an athlete holds several world records its name should appear only once.
Select distinct AName from Athlete natural join Competition where AName = WorldRecordHolder;

-- c.  Provide the names of all athletes together with the names of the competitions in which they take part in. 
-- If an athlete takes part in several competitions, he appears in several result tuples.

Select AName, CName from Athlete a natural join Competition c, take_part t where a.ANr = t.ANr and c.CNr = t.CNr;

-- d. Provide a list of each country together with the number of medals won by athletes of this country. (The type of medal is not relevant.) 
-- Sort this ranking in descending order on the number of medals.
 Select Country, count(Medal) as medal_num from Athlete natural join take_part group by Country order by medal_num desc;
 
 -- e. Provide the names of all countries that have more than three athletes.
 Select Country, count(*) as Athlete_Num from Athlete group by Country having Athlete_Num >3;

-- f. Provide the name of the country that has the largest number of athletes.
 Select Country, count(*) as Athlete_Num from Athlete group by Athlete_Num having Athlete_Num >3;