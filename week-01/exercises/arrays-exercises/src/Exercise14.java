import java.util.Random;

public class Exercise14 {

    public static void main(String[] args) {

        String[] emailAddresses = {
                "lALVArez0@TeLEgRAPh.cO.uK",
                "cPAyneS1@SpHinN.COm",
                "spayLer2@google.Es",
                "csTEtSoN3@FOrBes.com",
                "BlEghOrn4@eArthlInK.NeT",
                "FMInchINtoN5@sCIEnTIfICAMERiCAN.cOm",
                "SGuTAnS6@wiKiPedIa.orG",
                "sWiCkINs7@usDA.GOv",
                "ctOOpE8@DiSCOVERY.CoM",
                "GArNOuld9@STOrifY.coM",
                "jalESIOA@FeeDbuRNER.CoM",
                "Gfeekb@HOsTGATOr.CoM",
                "ATIMLEttc@BLoGspoT.coM",
                "kCrebbInd@PRlOG.oRG",
                "bBorRowMaNE@triPADvIsoR.com",
                "gWiTLINgf@APPLe.cOm",
                "mbeEstong@UsnEWs.cOM",
                "sOlNEYH@ted.CoM",
                "rCHaRloI@unbLOG.fR",
                "gtRAINeRj@waLMARt.cOM",
                "AfOlBIggK@fC2.cOM",
                "TKevliNl@AsK.Com",
                "AYaxLEYM@HuBPageS.COM",
                "mFeaRneN@CraigSLIST.ORG",
                "nFaLdOEo@skyRoCk.cOM",
                "AWaNDRACkP@DRupAL.ORg",
                "LGABiTsq@LasT.fM",
                "IwhetHerr@Fc2.cOm",
                "gDetlOFFS@gOOgLE.CoM.Br",
                "NhuYChet@NyTiMES.cOm",
                "JVernaUu@qQ.cOm",
                "cPAdBERYV@UmIcH.eDu"
        };

        // The email addresses above are valid, but have inconsistent capitalization.
        // 1. Fix the capitalization. Normalize to all lowercase.
        // loop through list
        for (int i = 0; i < emailAddresses.length; i++){
            emailAddresses[i] = emailAddresses[i].toLowerCase();
        }

        // The following call should print all lowercase email addresses.
        printArray(emailAddresses);
    }

    public static void printArray(String[] arr) {

        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
