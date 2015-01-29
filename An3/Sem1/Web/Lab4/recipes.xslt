<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
<xsl:template match="/">
  <html>
      <head>
          <title>Food</title>
          <link href="style.css" rel="stylesheet" type="text/css"/>
      </head>
      <body>
        <div class="wrapper">
            <h1 class="title">Food</h1>       
                <table border="4">
                    <thead>
                        <tr>
                            <th class="name">Name</th>
                            <th class="ingredients">Ingredients</th>
                            <th class="prepTime">Preparation Time</th>
                            <th class="cookTime">Cooking Time</th>
                            <th class="prepSteps">Preparation steps</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <xsl:for-each select="recipes/recipy">
                        <xsl:sort select="preptime" order="descending"/>
                        <xsl:sort select="cooktime" order="descending"/>
                            <tr>
                                <td class="name">
                                    <h2><xsl:value-of select="name"/></h2>
                                    <div class="description"><xsl:value-of select="description"/></div>
                                </td>
                                
                                <td class="ingredients">
                                    <xsl:value-of select="ingredients"/>
                                 </td>
                                <td class="prepTime">
                                    <xsl:value-of select="preptime"/> h
                                </td>
                                <td class="cookTime">
                                    <xsl:value-of select="cooktime"/> h
                                </td>
                                <td class="prepSteps">
                                    <xsl:value-of select="prepSteps"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                            
                    </tbody>
                </table>
        </div>
      </body>
      </html>
</xsl:template>

</xsl:stylesheet>