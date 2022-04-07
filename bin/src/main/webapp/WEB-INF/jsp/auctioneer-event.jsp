<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Document</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="/css/event.css" />
        <script src="/webjars/jquery/jquery.min.js"></script>
        <script src="/webjars/sockjs-client/sockjs.min.js"></script>
        <script src="/webjars/stomp-websocket/stomp.min.js"></script>
        <script src="/js/auctioneerbid.js"></script>
    </head>

    <body>


        <!-- navbar start -->
        <nav style="z-index: 1000; position: sticky; top: 0;"
            class="navbar navbar-expand-sm navbar-light bg-white border-bottom"> <a
                class="navbar-brand ml-2 font-weight-bold" href="#"><span id="burgundy">Proxi</span><span
                    id="orange">Bid</span></a> <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarColor" aria-controls="navbarColor" aria-expanded="false"
                aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
            <div class="collapse navbar-collapse" id="navbarColor">
                <ul class="navbar-nav">
                    <!-- <li class="nav-item rounded bg-light search-nav-item"><input type="text" id="search" class="bg-light" placeholder="Search bread, cakes, desserts"><span class="fa fa-search text-muted"></span></li>
              <li class="nav-item"><a class="nav-link" href="#"><span class="fa fa-user-o"></span><span class="text">Login</span></a> </li>
              <li class="nav-item "><a class="nav-link" href="#"><span class="fa fa-shopping-cart"></span><span class="text">Cart</span></a> </li> -->
                </ul>
            </div>
        </nav>
        <!-- navbar end -->




        <section>
            <div class="tabs">
                <ul class="tab-links">
                    <li class="active"><a href="#tab1">Catalog</a></li>
                    <li><a href="#tab2"> Terms of Sale</a></li>
                </ul>

                <div class="tab-content">
                    <div id="tab1" class="tab active">
                        <div class="container">
                            <c:forEach var="c" items="${catalog}">
                                <div class="card">
                                    <img src="/catalogimage/${c.itemImage}" style=" border: 5px solid #555; "
                                        class="card-img-top" />
                                    <div class="card-body">
                                        <h5 class="card-title">${c.itemId}.${c.itemName}</h5>
                                        <p class="card-text">
                                            ${c.itemDesc}
                                        </p>







                                        <div id="name-from">

                                            <div class="container-fluid">

                                                <div class="row">
                                                    <div>
                                                    <small id="${c.itemId}auction" class="text-muted"></small> 
                                                    </div>
                                                    <div class="input-group">
                                                    <button  class="btn btn-success" id="winbid" onClick="finish(`${c.itemId}`,`${eventNo}`,`${c.itemId}`)" style="display:none" >Accept</button>
                                                    </div>
                                                    <div class="offset-md-8">
                                                        

                                                        


                                                        <div class="input-group">

                                                            <input type="text" value="${c.itemStartBid}" id="${c.itemId}t"
                                                                autofocus class="form-control">

                                                            <div class="input-group-append">

                                                                <button class="btn btn-success" id="${c.itemId}" onClick="trigger(this.id)">Start
                                                                    Bid</button>

                                                            </div>



                                                        </div>



                                                    </div>

                                                </div>

                                            </div>

                                        </div>








                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div id="tab2" class="tab">
                        <h3>terms of sale information here</h3>
                        <p>Special Terms
                            BY REGISTERING TO BID, YOU ARE OPTING IN TO OUR EMAIL MAILING LIST.
                            PLEASE READ THIS ENTIRE AGREEMENT.
                            TERMS AND CONDITIONS OF SALE
                            1. All items are sold AS IS, WHERE IS with all faults. ALL SALES ARE FINAL - NO REFUNDS AND
                            NO EXCHANGES. All items are available for inquiries prior to bidding. Written and oral
                            descriptions are our opinions and should in no way be construed as a guarantee of any kind
                            as to authenticity, age, condition, materials or any other feature of items being sold.
                            AuctionBarn attempts to catalog every lot correctly and will attempt to point out any
                            defects but will not be responsible or liable for the correctness of the catalog or other
                            descriptions of the lot. We recommend prospective bidders examine all items in which they
                            have an interest. By placing a bid, either in person, by phone, absentee or via the
                            Internet, you signify that you have examined the items as fully as you desire to or that you
                            have chosen not to examine them. If you require absolute certainty in all areas of
                            authenticity, and the results of your evaluation leave uncertainty in your mind, we
                            recommend you have an expert examine the lot for you or do not bid on the item in question.
                            2. All property is sold AS IS, WHERE IS and neither AuctionBarn nor the consignor makes any
                            warranties or representations of any kind or nature with respect to property or its value,
                            and in no event shall be responsible for the correctness of description, genuineness,
                            attribution, provenance, authenticity, authorship, completeness, condition of property or
                            estimate of value. No statement (oral or written) in this catalog, at the sale, or elsewhere
                            shall be deemed such a warranty or representation, or any assumption of responsibility. All
                            measurements are approximate.
                            3. Payment is expected immediately following the end of the auction, at the latest three
                            days after the auction. If you cannot make the payment by that time, you must contact us to
                            make other arrangements. The successful bidder is the highest bidder acknowledged by the
                            auctioneer. In the event of any dispute between bidders, or in the event of doubt on
                            AuctionBarn's part as to the validity of any bid, the auctioneer will have the final
                            discretion either to determine the successful bidder or to re-offer and resell the article
                            in dispute. If any dispute arises after the sale, AuctionBarn's sale record is conclusive.
                            4. The purchaser assumes full responsibility for items at the fall of the hammer.
                            5. There is a buyer's premium of 22% added to the Hammer price in this auction.
                            6. For all items shipped to a California address, 10.25% sales tax will be added to your
                            invoice.
                            7. PAYMENT: We accept bank wire transfers, personal or company check, cashier's check, money
                            order, Visa, MasterCard, Discover and American Express. We also accept PayPal payments. For
                            items purchased online, you will be emailed an invoice after the auction ends. This email
                            provides a link that allows you to pay immediately and securely online. It is your
                            responsibility to contact us at 818-481-3042 or email us at auctionbarn.estates@gmail.com if
                            you are unable to pay through our online check-out system within three days. If paying by
                            check, money order, or cashier's check, AuctionBarn reserves the right to hold all purchases
                            until the check has cleared to the satisfaction of AuctionBarn and its bank. If we are
                            prevented by fire, theft, or any other reason whatsoever from delivering any property to the
                            purchaser, our liability shall be limited to the sum actually paid, by the purchaser for
                            such property.

                            6. Refunds may be given in the event of a discrepancy. Be assured, all items are covered by
                            our money back guarantee, buyers have thirty days from the date that they receive their
                            item(s) to return them at our expense if they are found to have been incorrectly described,
                            we require a written certification from a specialist that shows the discrepancy. This
                            guarantee does not apply to the dollar appraisal amount shown.
                            7. AuctionBarn reserves the right to withdraw any lot at any time before, during, or after
                            the auction without liability.
                            8. The auctioneer reserves the right to reject any bid at any time and as his sole
                            discretion.
                            9. A range of estimates is given on each lot with a low and a high estimate. Estimates
                            should not be relied on as a prediction of the actual selling or resale price.
                            10. On the fall of the auctioneer's hammer, ownership of the offered lot will pass to the
                            highest bidder acknowledged by the auctioneer, subject to fulfillment by such bidder, of all
                            the conditions set forth herein, and such bidder thereupon a) assumes full risk and
                            responsibility thereof, but not limited to, insurance, fire, theft, removal and storage or
                            damage from any and all causes, and b) will pay the full purchase price thereof or such part
                            as we may require. In addition to other remedies available to us by law, we reserve the
                            right to impose a late charge of 1 1/2% per month of the total purchase price if payment is
                            not made in accordance with the conditions set forth herein. REGARDING ANY PURCHASER WHO IS
                            REPRESENTED BY A BIDDER: BIDDERS ARE PERSONALLY AND INDIVIDUALLY RESPONSIBLE FOR ANY
                            OBLIGATIONS OF THE PURCHASER SET FORTH IN THE TERMS AND CONDITIONS OF SALE. If any
                            applicable conditions herein are not complied with by the purchaser, in addition to other
                            remedies available to us and the Consigner by law, including, without limitation, the right
                            to hold the purchaser liable for the total purchase price, we at our option may either, a)
                            cancel the sale, retaining as liquidated damages all payments made by the purchaser, or b)
                            resell the property at public auction without reserve, and the purchaser will be liable for
                            any deficiency costs including handling charges, the expenses of both sales, our commissions
                            on both sales at our regular rates, reasonable attorney's fees, incidental damages, and all
                            other charges due hereunder. In the event that such a buyer pays a portion of the purchase
                            price for any or all lots purchased, AuctionBarn shall apply the payment received to such
                            lot or lots that AuctionBarn, in its sole discretion deems appropriate. In the case of
                            default, purchaser shall be liable for legal fees and expenses. In addition, a defaulting
                            purchaser will be deemed to have granted us a security interest in, and we may retain as
                            collateral security for such purchaser's obligations to us, any property in our possession
                            owned by such purchaser.
                            11. In the unlikely event we lose our internet connection, AuctionBarn reserves the right in
                            its sole and absolute discretion to cancel the remainder of the auction.
                            12. **Auctioneer has final discretion on sale of an item. His word is final.**
                            13. Unless the sale is advertised and announced as 'without reserve', each lot is offered
                            subject to a reserve and the Company may implement such reserves by bidding through its
                            representatives on behalf of the Consignors. In certain instances, the Consignor may pay
                            less than the standard commission rate where the Company or its representative is a
                            successful bidder on behalf of the Consignor. Where the Consignor is indebted to the
                            Company, the Company may have an interest in the offered lots and the proceeds there from
                            other than the broker's Commissions, and all sales are subject to any such interest.
                            **IMPORTANT - PLEASE READ***
                            AS YOU HAVE ACKNOWLEDGED BY AGREEING TO THE TERMS OF THIS AUCTION, THERE ARE NO REFUNDS OR
                            EXCHANGES unless you obtain and submit a written evaluation showing a discrepancy from an
                            Expert or Specialist in the field.
                            For this auction we may (at our discretion) combine multiple purchases to reduce shipping
                            charges, all lots will have a separate handling charge. Our insurance company does not allow
                            pick-up of your items, all items must be shipped.
                            Will Call of items is not allowed. Coming to our offices to view items is not allowed at
                            this time. ***INSURANCE IS INCLUDED IN ABOVE PRICES*** ***IMPORTANT*** - Should expedited
                            shipping be requested, additional fees will apply. - We reserve the right to adjust shipping
                            charges. - Substantial Additional fees apply when shipping Internationally. - All items are
                            shipped insured. Please be sure that you understand our shipping policies prior to bidding.
                            If you are unclear, please contact us at auctionbarn.estates@gmail.com for clarification. If
                            you are still unsure, please enjoy the auction however, please do not bid.
                            IMPORTANT Notices & Disclaimers - Please Read
                            Regarding Appraised Jewelry and Loose Stones - AuctionBarn sends them out to a third party
                            for Retail Replacement Value Appraisals, which are the most common. These types of
                            evaluations are written for insurance needs. This would be the cost to replace the jewelry
                            with an item of equal quality and kind, considered to be the current market price or the
                            replacement value. This type of appraisal is done to protect you in the event of damage or
                            loss of a valuable item. Our appraisals are conducted by independent 3rd Party appraisal
                            firms that utilizes Certified GIA Gemologist. Appraisals should be used for verification of
                            gemstone and/or metal weight, size, and grade only, not actual value. Appraisal value should
                            be considered for insurance purposes only, not actual value, as price varies greatly from
                            retail locations thru out the world. Items should not be purchased with the expectation to
                            resell for appraisal price, or for profit.</p>
                    </div>



                </div>

            </div>

        </section>

        <br>
        <hr><br>



        <!-- <footer class="footer" style="height: 20px;">
        <p >Proxibid </p>
      </footer> -->

        <footer style="text-align: center; color: white;"> ProxiBid All rights reserved</footer>



        <script>
            $(document).ready(function () {
                $('.tabs .tab-links a').on('click', function (e) {
                    var currentAttrValue = $(this).attr('href');

                    // Show/Hide Tabs
                    $('.tabs ' + currentAttrValue).fadeIn(400).siblings().hide();
                    // Change/remove current tab to active
                    $(this).parent('li').addClass('active').siblings().removeClass('active');

                    e.preventDefault();


                });
            });
        </script>

        <script>
            window.onscroll = function () { myFunction() };

            var header = document.getElementById("myHeader");
            var sticky = header.offsetTop;

            function myFunction() {
                if (window.pageYOffset > sticky) {
                    header.classList.add("sticky");
                } else {
                    header.classList.remove("sticky");
                }
            }
        </script>


        <script>

            $(document).ready(function () {
                var dh = $(document).height(),
                    fh = $(".footer").outerHeight(),
                    bh = $("body").height();

                if (bh < dh) {
                    $(".footer").css({
                        "position": "fixed",
                        "display": "block",
                        "top": (dh - fh) + "px"
                    });
                }
            });

        </script>




    </body>

    </html>