CREATE TABLE featuretype_service(
	featuretype INTEGER,
	service INTEGER,
	boundingbox GEOMETRY,
	maxx DOUBLE PRECISION,
	minx DOUBLE PRECISION,
	maxy DOUBLE PRECISION,
	miny DOUBLE PRECISION,
	PRIMARY KEY (featuretype, service),
	FOREIGN KEY (featuretype) REFERENCES featuretype(id),
	FOREIGN KEY (service) REFERENCES service(id)	
);

CREATE INDEX ON featuretype_service USING GIST (boundingbox gist_geometry_ops_nd);