# Data preparation

TOPMed Imputation Server accepts VCF files compressed with [bgzip](http://samtools.sourceforge.net/tabix.shtml). Please make sure the following requirements are met:

- Create a separate vcf.gz file for each chromosome.
- Variations must be sorted by genomic position.
- GRCh37 or GRCh38 coordinates are required.
  - If your input data is GRCh37/hg19, please ensure chromosomes are encoded without prefix (e.g. 20).
  - If your input data is GRCh38/hg38, please ensure chromosomes are encoded with prefix 'chr' (e.g. chr20). 
- VCF files need to be version 4.2 (or lower)
- Due to server resource requirements, there is a maximum of 25k samples per chromosome per job (and a minimum of 20 samples). Please see the FAQ for details. 

!!! note
    Multiple \*.vcf.gz files (one per chromosome) can be uploaded as part of a single job.



## Quality Control for HRC, 1000G and CAAPA imputation

Will Rayner provides a great toolbox to prepare data: [HRC or 1000G Pre-imputation Checks](http://www.well.ox.ac.uk/~wrayner/tools/).

The main steps for HRC are:

### Download tool and sites

````sh
wget http://www.well.ox.ac.uk/~wrayner/tools/HRC-1000G-check-bim-v4.2.7.zip
wget ftp://ngs.sanger.ac.uk/production/hrc/HRC.r1-1/HRC.r1-1.GRCh37.wgs.mac5.sites.tab.gz
````

### Convert ped/map to bed

````sh
plink --file <input-file> --make-bed --out <output-file>
````

### Create a frequency file

````sh
plink --freq --bfile <input> --out <freq-file>
````

### Execute script

````sh
perl HRC-1000G-check-bim.pl -b <bim file> -f <freq-file> -r HRC.r1-1.GRCh37.wgs.mac5.sites.tab -h
sh Run-plink.sh
````

### Create vcf using [VcfCooker](http://genome.sph.umich.edu/wiki/VcfCooker)

````sh
vcfCooker --in-bfile <bim file> --ref <reference.fasta>  --out <output-vcf> --write-vcf
bgzip <output-vcf>
````
## Additional Tools

### Convert ped/map files to VCF files

Several tools are available:
 [plink2](https://www.cog-genomics.org/plink2/),
 [BCFtools](https://samtools.github.io/bcftools) or [VcfCooker](http://genome.sph.umich.edu/wiki/VcfCooker).  

````sh
plink --ped study_chr1.ped --map study_chr1.map --export vcf-4.2 --recode vcf --out study_chr1
````

Create a sorted vcf.gz file using [BCFtools](https://samtools.github.io/bcftools):

````sh
bcftools sort study_chr1.vcf -Oz -o study_chr1.vcf.gz
````

### CheckVCF

Use [checkVCF](https://github.com/zhanxw/checkVCF) to ensure that the VCF files are valid. checkVCF proposes "Action Items" (e.g. upload to sftp server), which can be ignored. Only the validity should be checked with this command.

````sh
checkVCF.py -r human_g1k_v37.fasta -o out mystudy_chr1.vcf.gz
````
