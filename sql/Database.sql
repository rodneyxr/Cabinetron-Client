--CREATE TABLE InventoryPart(
--    InventoryPartId varchar(128),
--	PartId	varchar(128),
--	Quantity double,
--	Location varchar(64),
--	PRIMARY KEY(InventoryPartId)
--);
CREATE TABLE InventoryItem(
	varchar stockType
    inventoryItemID varchar(128),
	itemID	varchar(128),
	Quantity double,
	Location varchar(64),
	PRIMARY KEY(InventoryPartId)
);

CREATE TABLE InventoryProduct(
    InventoryPartId varchar(128),
	productTemplateID	varchar(128),
	Quantity double,
	Location varchar(64),
	PRIMARY KEY(InventoryPartId)
);

CREATE TABLE Part(
	PartId		varchar(128),
	PartNumber	varchar(64),
	PartVendor		varchar(64),
	PartName	varchar(255),
	PartUnit	varchar(64),
	PartExternalNumber	varchar(64),
	PRIMARY KEY(PartId)
);

CREATE TABLE ProductTemplate(
	templateID varchar(128),
	productNumber varchar(20),
	productDescription varchar(255),
	PRIMARY KEY(templateID)
);
CREATE TABLE ProductTemplatePart(
	productTemplateID varchar(128),
	partID varchar(128),
	quantity double
	PRIMARY KEY(productTemplateID);
);


// EXAMPLE QUERIES
//Insert data

// Add a new part

INSERT INTO Part(PartId,PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES
				('16218c11-c600-4f6c-980f-9c94f73ab524','PARTNUMBER1','Vendor1','partName1','Pieces','externPartNum1');



INSERT INTO Part(PartId, PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES
				(0,'PARTNUMBER12','Vendor12','partName12','Pieces','externPartNum12');
INSERT INTO Part(PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES
				('PARTNUMBER1','Vendor1','partName1','Pieces','externPartNum1');
INSERT INTO Part(PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES
				('PARTNUMBER2','Vendor2','partName2','Pieces','externPartNum2');
INSERT INTO Part(PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES
				('PARTNUMBER3','Vendor3','partName3','Pieces','externPartNum3');
INSERT INTO Part(PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES
				('PARTNUMBER4','Vendor4','partName4','Pieces','externPartNum4');
INSERT INTO Part(PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES
				('PARTNUMBER5','Vendor5','partName5','Pieces','externPartNum5');
// add inventory part
INSERT INTO InventoryPart(InventoryPartId,PartId,Quantity,Location) VALUES
							(0,0,12.5,'Facility 1 Warehouse 1');
						
// Retrieve Row by Part number
SELECT * FROM Part LEFT JOIN InventoryPart ON InventoryPart.PartId = 0 WHERE Part.PartId = 0;

