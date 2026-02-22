  
   
  Feature: Open Library Author API Validation
  @API @Task3 @TC_03
  Scenario: Validate specific author details from Open Library
    When I request details for author with ID "OL1A"
    Then the personal name should be "Sachi Rautroy"
    And the alternate names should include "Yugashrashta Sachi Routray"